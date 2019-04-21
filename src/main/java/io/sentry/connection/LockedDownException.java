package io.sentry.connection;

/**
 * Exception thrown when attempting to send Events while in a lockdown.
 */
public class LockedDownException extends RuntimeException {
	private static final long serialVersionUID = -1658469553697997454L;
}
