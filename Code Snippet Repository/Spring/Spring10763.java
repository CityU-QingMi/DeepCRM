	@Test
	public void requestUriWithTrailingSlash() throws Exception {
		this.request.addHeader(X_FORWARDED_PREFIX, "/");
		this.request.setContextPath("/app");
		this.request.setRequestURI("/app/path/");
		HttpServletRequest actual = filterAndGetWrappedRequest();

		assertEquals("", actual.getContextPath());
		assertEquals("/path/", actual.getRequestURI());
	}
