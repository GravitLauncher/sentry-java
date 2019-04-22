package io.sentry.marshaller;

import java.io.IOException;
import java.io.OutputStream;

import io.sentry.event.Event;

/**
 * Marshaller allows to serialise a {@link Event} and sends over a stream.
 */
public interface Marshaller {
	/**
	 * OutputStream delegating every call except for {@link #close()} to an other
	 * OutputStream.
	 */
	final class UncloseableOutputStream extends OutputStream {
		private final OutputStream originalStream;

		/**
		 * Creates an OutputStream which will not delegate the {@link #close()}
		 * operation.
		 *
		 * @param originalStream
		 *            original stream to encapsulate.
		 */
		public UncloseableOutputStream(OutputStream originalStream) {
			this.originalStream = originalStream;
		}

		@Override
		public void close() throws IOException {
		}

		@Override
		public void flush() throws IOException {
			originalStream.flush();
		}

		@Override
		public void write(byte[] b) throws IOException {
			originalStream.write(b);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			originalStream.write(b, off, len);
		}

		@Override
		public void write(int b) throws IOException {
			originalStream.write(b);
		}
	}

	/**
	 * Returns the HTTP Content-Encoding, if applicable, or null.
	 *
	 * @return the HTTP Content-Encoding, if applicable, or null.
	 */
	String getContentEncoding();

	/**
	 * Returns the HTTP Content-Type, if applicable, or null.
	 *
	 * @return the HTTP Content-Type, if applicable, or null.
	 */
	String getContentType();

	/**
	 * Serialises an event and sends it through an {@code OutputStream}.
	 * <p>
	 * The marshaller should not close the given stream, use
	 * {@link UncloseableOutputStream} to prevent automatic calls to
	 * {@link OutputStream#close()}.
	 *
	 * @param event
	 *            event to serialise.
	 * @param destination
	 *            destination stream.
	 * @throws IOException
	 *             on write error
	 */
	void marshall(Event event, OutputStream destination) throws IOException;
}
