package io.sentry.time;

import java.util.Date;

/**
 * Clock implementation that defers to the system clock. Should be used outside
 * of tests.
 */
public class SystemClock implements Clock {
	@Override
	public Date date() {
		return new Date();
	}

	@Override
	public long millis() {
		return System.currentTimeMillis();
	}
}
