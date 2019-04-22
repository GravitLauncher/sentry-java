package io.sentry.connection;

/**
 * Exception thrown when attempting to send Events while in a lockdown.
 */
public class TooManyRequestsException extends ConnectionException {
	private static final long serialVersionUID = -1425193695905138794L;

	public TooManyRequestsException(String message, Throwable cause, Long recommendedLockdownTime,
			Integer responseCode) {
		super(message, cause, recommendedLockdownTime, responseCode);
	}
}
