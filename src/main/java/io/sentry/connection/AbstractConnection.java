package io.sentry.connection;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import io.sentry.environment.SentryEnvironment;
import io.sentry.event.Event;
import io.sentry.util.Util;

/**
 * Abstract connection to a Sentry server.
 * <p>
 * Provide the basic tools to submit events to the server (authentication
 * header, dsn).<br>
 * To avoid spamming the network if and when Sentry is down, automatically lock
 * the connection each time a {@link ConnectionException} is caught.
 */
public abstract class AbstractConnection implements Connection {
	/**
	 * Current Sentry protocol version.
	 */
	public static final String SENTRY_PROTOCOL_VERSION = "6";
	private static final Logger logger = Logger.getLogger(AbstractConnection.class.getName());
	private static final Logger lockdownLogger = Logger.getLogger(AbstractConnection.class.getName() + ".lockdown");
	/**
	 * Value of the X-Sentry-Auth header.
	 */
	private final String authHeader;
	/**
	 * Set of callbacks that will be called when an exception occurs while
	 * attempting to send events to the Sentry server.
	 */
	private Set<EventSendCallback> eventSendCallbacks;
	private LockdownManager lockdownManager;

	/**
	 * Creates a connection based on the public and secret keys.
	 *
	 * @param publicKey
	 *            public key (identifier) to the Sentry server.
	 * @param secretKey
	 *            secret key (password) to the Sentry server.
	 */
	protected AbstractConnection(String publicKey, String secretKey) {
		lockdownManager = new LockdownManager();
		eventSendCallbacks = new HashSet<>();
		authHeader = "Sentry sentry_version=" + SENTRY_PROTOCOL_VERSION + "," + "sentry_client="
				+ SentryEnvironment.getSentryName() + "," + "sentry_key=" + publicKey
				+ (!Util.isNullOrEmpty(secretKey) ? ",sentry_secret=" + secretKey : "");
	}

	/**
	 * Add a callback that is called when an exception occurs while attempting to
	 * send events to the Sentry server.
	 *
	 * @param eventSendCallback
	 *            callback instance
	 */
	@Override
	public void addEventSendCallback(EventSendCallback eventSendCallback) {
		eventSendCallbacks.add(eventSendCallback);
	}

	/**
	 * Sends an event to the Sentry server.
	 *
	 * @param event
	 *            captured event to add in Sentry.
	 * @throws ConnectionException
	 *             whenever a temporary exception due to the connection happened.
	 */
	protected abstract void doSend(Event event) throws ConnectionException;

	/**
	 * Creates an authentication header for the Sentry protocol.
	 *
	 * @return an authentication header as a String.
	 */
	protected String getAuthHeader() {
		return authHeader;
	}

	@Override
	public final void send(Event event) throws ConnectionException {
		try {
			if (lockdownManager.isLockedDown())
				/*
				 * An exception is thrown to signal that this Event was not sent, which may be
				 * important in, for example, a BufferedConnection where the Event would be
				 * deleted from the Buffer if an exception isn't raised in the call to send.
				 */
				throw new LockedDownException();

			doSend(event);

			// success! re-open the floodgates
			lockdownManager.unlock();

			for (EventSendCallback eventSendCallback : eventSendCallbacks)
				try {
					eventSendCallback.onSuccess(event);
				} catch (Exception exc) {
					logger.fine("An exception occurred while running an EventSendCallback.onSuccess: "
							+ eventSendCallback.getClass().getName() + ' ' + exc);
				}
		} catch (ConnectionException e) {
			for (EventSendCallback eventSendCallback : eventSendCallbacks)
				try {
					eventSendCallback.onFailure(event, e);
				} catch (Exception exc) {
					logger.fine("An exception occurred while running an EventSendCallback.onFailure: "
							+ eventSendCallback.getClass().getName() + ' ' + exc);
				}

			boolean lockedDown = lockdownManager.lockdown(e);
			if (lockedDown)
				lockdownLogger.fine("Initiated a temporary lockdown because of exception: " + e.getMessage());

			throw e;
		}
	}

}
