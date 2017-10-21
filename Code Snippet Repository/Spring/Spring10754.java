	@Test
	public void sendRedirectWithRelativePath() throws Exception {
		this.request.addHeader(X_FORWARDED_PROTO, "https");
		this.request.addHeader(X_FORWARDED_HOST, "example.com");
		this.request.addHeader(X_FORWARDED_PORT, "443");
		this.request.setRequestURI("/parent/");

		String redirectedUrl = sendRedirect("foo/bar");
		assertEquals("https://example.com/parent/foo/bar", redirectedUrl);
	}
