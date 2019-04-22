package io.sentry.context;

/**
 * {@link ContextManager} that maintains a single {@link Context} instance
 * across the entire application.
 */
public class SingletonContextManager implements ContextManager {
	private final Context context = new Context();

	@Override
	public void clear() {
		context.clear();
	}

	/**
	 * Returns a singleton {@link Context} instance. Useful for single-user
	 * applications.
	 *
	 * @return a singleton {@link Context} instance.
	 */
	@Override
	public Context getContext() {
		return context;
	}
}
