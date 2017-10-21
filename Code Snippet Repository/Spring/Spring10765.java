	@Test
	public void requestUriEqualsContextPath() throws Exception {
		this.request.addHeader(X_FORWARDED_PREFIX, "/");
		this.request.setContextPath("/app");
		this.request.setRequestURI("/app");
		HttpServletRequest actual = filterAndGetWrappedRequest();

		assertEquals("", actual.getContextPath());
		assertEquals("/", actual.getRequestURI());
	}
