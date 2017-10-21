	@Test
	public void requestUriPreserveSemicolonContent() throws Exception {
		this.request.setContextPath("");
		this.request.setRequestURI("/path;a=b/with/semicolon");
		HttpServletRequest actual = filterAndGetWrappedRequest();

		assertEquals("", actual.getContextPath());
		assertEquals("/path;a=b/with/semicolon", actual.getRequestURI());
		assertEquals("http://localhost/path;a=b/with/semicolon", actual.getRequestURL().toString());
	}
