	@Test
	public void forwardedRequestInRemoveOnlyMode() throws Exception {
		this.request.setRequestURI("/mvc-showcase");
		this.request.addHeader(X_FORWARDED_PROTO, "https");
		this.request.addHeader(X_FORWARDED_HOST, "84.198.58.199");
		this.request.addHeader(X_FORWARDED_PORT, "443");
		this.request.addHeader("foo", "bar");

		this.filter.setRemoveOnly(true);
		this.filter.doFilter(this.request, new MockHttpServletResponse(), this.filterChain);
		HttpServletRequest actual = (HttpServletRequest) this.filterChain.getRequest();

		assertEquals("http://localhost/mvc-showcase", actual.getRequestURL().toString());
		assertEquals("http", actual.getScheme());
		assertEquals("localhost", actual.getServerName());
		assertEquals(80, actual.getServerPort());
		assertFalse(actual.isSecure());

		assertNull(actual.getHeader(X_FORWARDED_PROTO));
		assertNull(actual.getHeader(X_FORWARDED_HOST));
		assertNull(actual.getHeader(X_FORWARDED_PORT));
		assertEquals("bar", actual.getHeader("foo"));
	}
