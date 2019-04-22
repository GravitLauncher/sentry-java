package io.sentry.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder to assist the creation of {@link Breadcrumb}s.
 */
public class BreadcrumbBuilder {

	private Breadcrumb.Type type;
	private Date timestamp;
	private Breadcrumb.Level level;
	private String message;
	private String category;
	private Map<String, String> data;

	/**
	 * Build and return the {@link Breadcrumb} object.
	 *
	 * @return Breadcrumb
	 */
	public Breadcrumb build() {
		return new Breadcrumb(type, timestamp, level, message, category, data);
	}

	/**
	 * Category of the {@link Breadcrumb}.
	 *
	 * @param newCategory
	 *            String
	 * @return current BreadcrumbBuilder
	 */
	public BreadcrumbBuilder setCategory(String newCategory) {
		category = newCategory;
		return this;
	}

	/**
	 * Data related to the {@link Breadcrumb}. At least one of message or data is
	 * required.
	 *
	 * @param newData
	 *            Map of String to String
	 * @return current BreadcrumbBuilder
	 */
	public BreadcrumbBuilder setData(Map<String, String> newData) {
		data = newData;
		return this;
	}

	/**
	 * Level of the {@link Breadcrumb}.
	 *
	 * @param newLevel
	 *            {@link Breadcrumb.Level}
	 * @return current BreadcrumbBuilder
	 */
	public BreadcrumbBuilder setLevel(Breadcrumb.Level newLevel) {
		level = newLevel;
		return this;
	}

	/**
	 * Message of the {@link Breadcrumb}. At least one of message or data is
	 * required.
	 *
	 * @param newMessage
	 *            String
	 * @return current BreadcrumbBuilder
	 */
	public BreadcrumbBuilder setMessage(String newMessage) {
		message = newMessage;
		return this;
	}

	/**
	 * Timestamp of the {@link Breadcrumb}.
	 *
	 * @param newTimestamp
	 *            Date
	 * @return current BreadcrumbBuilder
	 */
	public BreadcrumbBuilder setTimestamp(Date newTimestamp) {
		timestamp = new Date(newTimestamp.getTime());
		return this;
	}

	/**
	 * Type of the {@link Breadcrumb}.
	 *
	 * @param newType
	 *            {@link Breadcrumb.Type}
	 * @return current BreadcrumbBuilder
	 */
	public BreadcrumbBuilder setType(Breadcrumb.Type newType) {
		type = newType;
		return this;
	}

	/**
	 * Adds to the related data for the {@link breadcrumb}.
	 *
	 * @param name
	 *            Name of the data
	 * @param value
	 *            Value of the data
	 * @return current BreadcrumbBuilder
	 */
	public BreadcrumbBuilder withData(String name, String value) {
		if (data == null)
			data = new HashMap<>();

		data.put(name, value);
		return this;
	}

}
