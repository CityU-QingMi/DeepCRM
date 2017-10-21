	public ServletServerHttpAsyncRequestControl(ServletServerHttpRequest request, ServletServerHttpResponse response) {
		Assert.notNull(request, "request is required");
		Assert.notNull(response, "response is required");

		Assert.isTrue(request.getServletRequest().isAsyncSupported(),
				"Async support must be enabled on a servlet and for all filters involved " +
				"in async request processing. This is done in Java code using the Servlet API " +
				"or by adding \"<async-supported>true</async-supported>\" to servlet and " +
				"filter declarations in web.xml. Also you must use a Servlet 3.0+ container");

		this.request = request;
		this.response = response;
	}
