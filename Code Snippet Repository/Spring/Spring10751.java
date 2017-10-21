	@Test
	public void contextPathPreserveEncoding() throws Exception {
		this.request.setContextPath("/app%20");
		this.request.setRequestURI("/app%20/path/");
		HttpServletRequest actual = filterAndGetWrappedRequest();

		assertEquals("/app%20", actual.getContextPath());
		assertEquals("/app%20/path/", actual.getRequestURI());
		assertEquals("http://localhost/app%20/path/", actual.getRequestURL().toString());
	}
