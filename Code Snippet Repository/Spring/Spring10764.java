	@Test
	public void requestUriPreserveEncoding() throws Exception {
		this.request.setContextPath("/app");
		this.request.setRequestURI("/app/path%20with%20spaces/");
		HttpServletRequest actual = filterAndGetWrappedRequest();

		assertEquals("/app", actual.getContextPath());
		assertEquals("/app/path%20with%20spaces/", actual.getRequestURI());
		assertEquals("http://localhost/app/path%20with%20spaces/", actual.getRequestURL().toString());
	}
