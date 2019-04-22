package io.sentry.config;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NoInitialContextException;

/**
 * Handles JNDI lookup of a provided key within the Sentry namespace.
 */
public final class JndiLookup {
	/**
	 * Lookup prefix for Sentry configuration in JNDI.
	 */
	private static final String JNDI_PREFIX = "java:comp/env/sentry/";
	private static final Logger logger = Logger.getLogger(JndiLookup.class.getName());

	/**
	 * Looks up for a JNDI definition of the provided key.
	 *
	 * @param key
	 *            name of configuration key, e.g. "dsn"
	 * @return the value as defined in JNDI or null if it isn't defined.
	 */
	public static String jndiLookup(String key) {
		String value = null;
		try {
			Context c = new InitialContext();
			value = (String) c.lookup(JNDI_PREFIX + key);
		} catch (NoInitialContextException e) {
			logger.info("JNDI not configured for Sentry (NoInitialContextEx)");
		} catch (NamingException e) {
			logger.info("No /sentry/" + key + " in JNDI");
		} catch (RuntimeException e) {
			logger.fine("Odd RuntimeException while testing for JNDI" + e);
		}
		return value;
	}

	private JndiLookup() {

	}
}
