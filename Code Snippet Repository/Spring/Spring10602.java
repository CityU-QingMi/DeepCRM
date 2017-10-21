	@Test
	public void decoratedNativeRequest() {
		HttpServletRequest decoratedRequest = new HttpServletRequestWrapper(servletRequest);
		HttpServletResponse decoratedResponse = new HttpServletResponseWrapper(servletResponse);
		ServletWebRequest request = new ServletWebRequest(decoratedRequest, decoratedResponse);
		assertSame(decoratedRequest, request.getNativeRequest());
		assertSame(decoratedRequest, request.getNativeRequest(ServletRequest.class));
		assertSame(decoratedRequest, request.getNativeRequest(HttpServletRequest.class));
		assertSame(servletRequest, request.getNativeRequest(MockHttpServletRequest.class));
		assertNull(request.getNativeRequest(MultipartRequest.class));
		assertSame(decoratedResponse, request.getNativeResponse());
		assertSame(decoratedResponse, request.getNativeResponse(ServletResponse.class));
		assertSame(decoratedResponse, request.getNativeResponse(HttpServletResponse.class));
		assertSame(servletResponse, request.getNativeResponse(MockHttpServletResponse.class));
		assertNull(request.getNativeResponse(MultipartRequest.class));
	}
