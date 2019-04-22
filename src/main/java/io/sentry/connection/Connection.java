package io.sentry.connection;

import java.io.Closeable;

import io.sentry.event.Event;

/**
 * Connection to a Sentry server, allowing to send captured events.
 */
public interface Connection extends Closeable {
	/**
	 * Add a callback that is called when an exception occurs while attempting to
	 * send events to the Sentry server.
	 *
	 * @param eventSendCallback
	 *            callback instance
	 */
	void addEventSendCallback(EventSendCallback eventSendCallback);

	/**
	 * Sends an event to the Sentry server.
	 *
	 * @param event
	 *            captured event to add in Sentry.
	 * @throws ConnectionException
	 *             Thrown when an Event send fails.
	 */
	void send(Event event) throws ConnectionException;

}
