	@Test
	public void sendRedirectWithLocationSlashSlashParentDotDot() throws Exception {
		this.request.addHeader(X_FORWARDED_PROTO, "https");
		this.request.addHeader(X_FORWARDED_HOST, "example.com");
		this.request.addHeader(X_FORWARDED_PORT, "443");

		String location = "//other.info/parent/../foo/bar";
		String redirectedUrl = sendRedirect(location);
		assertEquals("https:" + location, redirectedUrl);
	}
