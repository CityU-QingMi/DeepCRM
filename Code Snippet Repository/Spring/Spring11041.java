	@Test
	public void wasCasualServletFile() throws Exception {
		request.setContextPath("/test");
		request.setPathInfo("/foo");
		request.setServletPath("/foo");
		request.setRequestURI("/test/foo/foo");
		request.setAttribute(WEBSPHERE_URI_ATTRIBUTE, "/test/foo/foo");

		assertEquals("/foo", helper.getLookupPathForRequest(request));
	}
