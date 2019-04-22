package io.sentry.event.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The DebugMeta interface for Sentry allowing to add debug information about
 * ProGuard.
 */
public class DebugMetaInterface implements SentryInterface {
	/**
	 * Object that represents a single debug image.
	 */
	public static class DebugImage implements Serializable {
		private static final long serialVersionUID = 3383692841704342779L;
		private static final String DEFAULT_TYPE = "proguard";
		private final String uuid;
		private final String type;

		/**
		 * Construct a Proguard {@link DebugImage} with the provided UUID.
		 *
		 * @param uuid
		 *            UUID of the image.
		 */
		public DebugImage(String uuid) {
			this(uuid, DEFAULT_TYPE);
		}

		/**
		 * Construct a {@link DebugImage} with the provided UUID and type.
		 *
		 * @param uuid
		 *            UUID of the image.
		 * @param type
		 *            Type of the image.
		 */
		public DebugImage(String uuid, String type) {
			this.uuid = uuid;
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public String getUuid() {
			return uuid;
		}

		@Override
		public String toString() {
			return "DebugImage{" + "uuid='" + uuid + '\'' + ", type='" + type + '\'' + '}';
		}
	}

	private static final long serialVersionUID = 4104905603080966228L;
	/**
	 * Name of the exception interface in Sentry.
	 */
	public static final String DEBUG_META_INTERFACE = "debug_meta";

	private ArrayList<DebugImage> debugImages = new ArrayList<>();

	/**
	 * Adds a single {@link DebugImage} to the interface.
	 *
	 * @param debugImage
	 *            {@link DebugImage} to add.
	 */
	public void addDebugImage(DebugImage debugImage) {
		debugImages.add(debugImage);
	}

	public ArrayList<DebugImage> getDebugImages() {
		return debugImages;
	}

	@Override
	public String getInterfaceName() {
		return DEBUG_META_INTERFACE;
	}

	@Override
	public int hashCode() {
		return debugImages.hashCode();
	}

	@Override
	public String toString() {
		return "DebugMetaInterface{" + "debugImages=" + debugImages + '}';
	}
}
