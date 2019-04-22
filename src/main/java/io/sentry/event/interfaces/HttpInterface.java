package io.sentry.event.interfaces;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * The HTTP interface for Sentry allows to add an HTTP request to an event.
 */
public class HttpInterface implements SentryInterface {
	private static final long serialVersionUID = 598470211589537138L;
	/**
	 * Name of the HTTP interface in Sentry.
	 */
	public static final String HTTP_INTERFACE = "sentry.interfaces.Http";
	private final String requestUrl;
	private final String method;
	private final Map<String, Collection<String>> parameters;
	private final String queryString;
	private final Map<String, String> cookies;
	private final String remoteAddr;
	private final String serverName;
	private final int serverPort;
	private final String localAddr;
	private final String localName;
	private final int localPort;
	private final String protocol;
	private final boolean secure;
	private final boolean asyncStarted;
	private final String authType;
	private final String remoteUser;
	private final Map<String, Collection<String>> headers;
	private final String body;

	/**
	 * Creates an HTTP element for an {@link io.sentry.event.Event}.
	 *
	 * @param requestUrl
	 *            The full url including the scheme, host, path and query parameters
	 * @param method
	 *            The HTTP method
	 * @param parameters
	 *            Extra request parameters from either the query string or posted
	 *            form data.
	 * @param queryString
	 *            The query string
	 * @param cookies
	 *            Collection of values for each cookie
	 * @param remoteAddr
	 *            The remote ip address
	 * @param serverName
	 *            The server's hostname
	 * @param serverPort
	 *            The port from the server's hostname
	 * @param localAddr
	 *            The IP that received the request
	 * @param localName
	 *            The hostname of the IP that received the request
	 * @param localPort
	 *            The port that received the request
	 * @param protocol
	 *            The protocol name and version
	 * @param secure
	 *            True if the request was made using a secure channel
	 * @param asyncStarted
	 *            Servlet specific
	 * @param authType
	 *            Servlet specific
	 * @param remoteUser
	 *            The login of the user making the request
	 * @param headers
	 *            The headers sent with the request
	 * @param body
	 *            The request body
	 */
	public HttpInterface(String requestUrl, String method, Map<String, Collection<String>> parameters,
			String queryString, Map<String, String> cookies, String remoteAddr, String serverName, int serverPort,
			String localAddr, String localName, int localPort, String protocol, boolean secure, boolean asyncStarted,
			String authType, String remoteUser, Map<String, Collection<String>> headers, String body) {

		this.requestUrl = requestUrl;
		this.method = method;
		this.parameters = parameters;
		this.queryString = queryString;
		this.cookies = cookies;
		this.remoteAddr = remoteAddr;
		this.serverName = serverName;
		this.serverPort = serverPort;
		this.localAddr = localAddr;
		this.localName = localName;
		this.localPort = localPort;
		this.protocol = protocol;
		this.secure = secure;
		this.asyncStarted = asyncStarted;
		this.authType = authType;
		this.remoteUser = remoteUser;
		this.headers = headers;
		this.body = body;
	}
	// CHECKSTYLE.ON: ParameterNumber

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HttpInterface that = (HttpInterface) o;

		if (asyncStarted != that.asyncStarted)
			return false;
		if (localPort != that.localPort)
			return false;
		if (secure != that.secure)
			return false;
		if (serverPort != that.serverPort)
			return false;
		if (authType != null ? !authType.equals(that.authType) : that.authType != null)
			return false;
		if (!cookies.equals(that.cookies))
			return false;
		if (!headers.equals(that.headers))
			return false;
		if (localAddr != null ? !localAddr.equals(that.localAddr) : that.localAddr != null)
			return false;
		if (localName != null ? !localName.equals(that.localName) : that.localName != null)
			return false;
		if (method != null ? !method.equals(that.method) : that.method != null)
			return false;
		if (!parameters.equals(that.parameters))
			return false;
		if (protocol != null ? !protocol.equals(that.protocol) : that.protocol != null)
			return false;
		if (queryString != null ? !queryString.equals(that.queryString) : that.queryString != null)
			return false;
		if (remoteAddr != null ? !remoteAddr.equals(that.remoteAddr) : that.remoteAddr != null)
			return false;
		if (remoteUser != null ? !remoteUser.equals(that.remoteUser) : that.remoteUser != null)
			return false;
		if (!requestUrl.equals(that.requestUrl))
			return false;
		if (serverName != null ? !serverName.equals(that.serverName) : that.serverName != null)
			return false;
		if (body != null ? !body.equals(that.body) : that.body != null)
			return false;

		return true;
	}

	public String getAuthType() {
		return authType;
	}

	public String getBody() {
		return body;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public Map<String, Collection<String>> getHeaders() {
		return Collections.unmodifiableMap(headers);
	}

	@Override
	public String getInterfaceName() {
		return HTTP_INTERFACE;
	}

	public String getLocalAddr() {
		return localAddr;
	}

	public String getLocalName() {
		return localName;
	}

	public int getLocalPort() {
		return localPort;
	}

	public String getMethod() {
		return method;
	}

	public Map<String, Collection<String>> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}

	public String getProtocol() {
		return protocol;
	}

	public String getQueryString() {
		return queryString;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public String getServerName() {
		return serverName;
	}

	public int getServerPort() {
		return serverPort;
	}

	@Override
	public int hashCode() {
		int result = requestUrl.hashCode();
		result = 31 * result + (method != null ? method.hashCode() : 0);
		result = 31 * result + parameters.hashCode();
		return result;
	}

	public boolean isAsyncStarted() {
		return asyncStarted;
	}

	public boolean isSecure() {
		return secure;
	}

	@Override
	public String toString() {
		return "HttpInterface{" + "requestUrl='" + requestUrl + '\'' + ", method='" + method + '\'' + ", queryString='"
				+ queryString + '\'' + ", parameters=" + parameters + '}';
	}
}
