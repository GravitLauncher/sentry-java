package io.sentry.connection;

import java.io.IOException;

import io.sentry.event.Event;

/**
 * Connection that drops events.
 *
 * Only use it when no DSN is set.
 */
public class NoopConnection extends AbstractConnection {
	/**
	 * Creates a connection that drops events.
	 */
	public NoopConnection() {
		super(null, null);
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	protected void doSend(Event event) throws ConnectionException {
	}
}
