	public MockHttpServletRequest buildRequest(ServletContext servletContext) {
		Charset charset = getCharset();
		String httpMethod = this.webRequest.getHttpMethod().name();
		UriComponents uriComponents = uriComponents();
		String path = uriComponents.getPath();

		MockHttpServletRequest request = new HtmlUnitMockHttpServletRequest(
				servletContext, httpMethod, (path != null ? path : ""));
		parent(request, this.parentBuilder);
		String host = uriComponents.getHost();
		request.setServerName(host != null ? host : "");  // needs to be first for additional headers
		authType(request);
		request.setCharacterEncoding(charset.name());
		content(request, charset);
		contextPath(request, uriComponents);
		contentType(request);
		cookies(request);
		headers(request);
		locales(request);
		servletPath(uriComponents, request);
		params(request, uriComponents);
		ports(uriComponents, request);
		request.setProtocol("HTTP/1.1");
		request.setQueryString(uriComponents.getQuery());
		String scheme = uriComponents.getScheme();
		request.setScheme(scheme != null ? scheme : "");
		request.setPathInfo(null);

		return postProcess(request);
	}
