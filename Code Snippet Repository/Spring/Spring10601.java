	@Test
	public void nativeRequest() {
		assertSame(servletRequest, request.getNativeRequest());
		assertSame(servletRequest, request.getNativeRequest(ServletRequest.class));
		assertSame(servletRequest, request.getNativeRequest(HttpServletRequest.class));
		assertSame(servletRequest, request.getNativeRequest(MockHttpServletRequest.class));
		assertNull(request.getNativeRequest(MultipartRequest.class));
		assertSame(servletResponse, request.getNativeResponse());
		assertSame(servletResponse, request.getNativeResponse(ServletResponse.class));
		assertSame(servletResponse, request.getNativeResponse(HttpServletResponse.class));
		assertSame(servletResponse, request.getNativeResponse(MockHttpServletResponse.class));
		assertNull(request.getNativeResponse(MultipartRequest.class));
	}
