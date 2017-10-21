	@Test
	public void cookies() {
		Cookie cookie1 = new Cookie("foo", "bar");
		Cookie cookie2 = new Cookie("baz", "qux");
		request.setCookies(cookie1, cookie2);

		Cookie[] cookies = request.getCookies();
		List<String> cookieHeaders = Collections.list(request.getHeaders("Cookie"));

		assertEquals(2, cookies.length);
		assertEquals("foo", cookies[0].getName());
		assertEquals("bar", cookies[0].getValue());
		assertEquals("baz", cookies[1].getName());
		assertEquals("qux", cookies[1].getValue());
		assertEquals(Arrays.asList("foo=bar", "baz=qux"), cookieHeaders);
	}
