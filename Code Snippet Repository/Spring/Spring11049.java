	@Test
	public void wasDefaultServletRoot() throws Exception {
		request.setContextPath("/test");
		request.setPathInfo("/");
		request.setServletPath("");
		request.setRequestURI("/test/");
		request.setAttribute(WEBSPHERE_URI_ATTRIBUTE, "/test/");

		assertEquals("/", helper.getLookupPathForRequest(request));
	}
