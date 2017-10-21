	private MockServerRequest(HttpMethod method, URI uri, String contextPath, MockHeaders headers,
			MultiValueMap<String, HttpCookie> cookies, @Nullable Object body,
			Map<String, Object> attributes, MultiValueMap<String, String> queryParams,
			Map<String, String> pathVariables, @Nullable WebSession session, @Nullable Principal principal) {

		this.method = method;
		this.uri = uri;
		this.pathContainer = RequestPath.parse(uri, contextPath);
		this.headers = headers;
		this.cookies = cookies;
		this.body = body;
		this.attributes = attributes;
		this.queryParams = queryParams;
		this.pathVariables = pathVariables;
		this.session = session;
		this.principal = principal;
	}
