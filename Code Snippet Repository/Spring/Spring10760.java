	@Test
	public void sendRedirectWhenRequestOnlyAndXForwardedThenUsesRelativeRedirects() throws Exception {
		this.request.addHeader(X_FORWARDED_PROTO, "https");
		this.request.addHeader(X_FORWARDED_HOST, "example.com");
		this.request.addHeader(X_FORWARDED_PORT, "443");
		this.filter.setRelativeRedirects(true);

		String location = sendRedirect("/a");

		assertEquals("/a", location);
	}
