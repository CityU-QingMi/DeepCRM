	@Test
	public void sendRedirectWithContextPath() throws Exception {
		this.request.addHeader(X_FORWARDED_PROTO, "https");
		this.request.addHeader(X_FORWARDED_HOST, "example.com");
		this.request.addHeader(X_FORWARDED_PORT, "443");
		this.request.setContextPath("/context");

		String redirectedUrl = sendRedirect("/context/foo/bar");
		assertEquals("https://example.com/context/foo/bar", redirectedUrl);
	}
