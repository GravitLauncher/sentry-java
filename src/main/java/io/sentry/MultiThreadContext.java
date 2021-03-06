package io.sentry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MultiThreadContext {
	private static final InheritableThreadLocal<Map<String, String>> inheritableThreadLocal = new InheritableThreadLocal<Map<String, String>>() {
		@Override
		protected Map<String, String> childValue(Map<String, String> parentValue) {
			if (parentValue == null)
				return null;
			return new HashMap<>(parentValue);
		}
	};

	/**
	 * Clear all entries in the MDC.
	 */
	public static void clear() {
		Map<String, String> map = inheritableThreadLocal.get();
		if (map != null) {
			map.clear();
			inheritableThreadLocal.remove();
		}
	}

	/**
	 * Get the context identified by the <code>key</code> parameter.
	 */
	public static String get(String key) {
		Map<String, String> map = inheritableThreadLocal.get();
		if (map != null && key != null)
			return map.get(key);
		else
			return null;
	}

	/**
	 * Return a copy of the current thread's context map. Returned value may be
	 * null.
	 *
	 */
	public static Map<String, String> getCopyOfContextMap() {
		Map<String, String> oldMap = inheritableThreadLocal.get();
		if (oldMap != null)
			return new HashMap<>(oldMap);
		else
			return null;
	}

	/**
	 * Returns the keys in the MDC as a {@link Set} of {@link String}s The returned
	 * value can be null.
	 *
	 * @return the keys in the MDC
	 */
	public static Set<String> getKeys() {
		Map<String, String> map = inheritableThreadLocal.get();
		if (map != null)
			return map.keySet();
		else
			return null;
	}

	/**
	 * Put a context value (the <code>val</code> parameter) as identified with the
	 * <code>key</code> parameter into the current thread's context map. Note that
	 * contrary to log4j, the <code>val</code> parameter can be null.
	 *
	 * <p>
	 * If the current thread does not have a context map it is created as a side
	 * effect of this call.
	 *
	 * @throws IllegalArgumentException
	 *             in case the "key" parameter is null
	 */
	public static void put(String key, String val) {
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");
		Map<String, String> map = inheritableThreadLocal.get();
		if (map == null) {
			map = new HashMap<>();
			inheritableThreadLocal.set(map);
		}
		map.put(key, val);
	}

	/**
	 * Remove the the context identified by the <code>key</code> parameter.
	 */
	public static void remove(String key) {
		Map<String, String> map = inheritableThreadLocal.get();
		if (map != null)
			map.remove(key);
	}

	public static void setContextMap(Map<String, String> contextMap) {
		inheritableThreadLocal.set(new HashMap<>(contextMap));
	}
}
