	@Test
	public void cookies() {
		Cookie cookie = new Cookie("foo", "bar");
		cookie.setPath("/path");
		cookie.setDomain("example.com");
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);

		response.addCookie(cookie);

		assertEquals("foo=bar; Path=/path; Domain=example.com; " +
				"Max-Age=0; Expires=Thu, 1 Jan 1970 00:00:00 GMT; " +
				"Secure; HttpOnly", response.getHeader(HttpHeaders.SET_COOKIE));
	}
