	@Test
	public void testSetAndResolveLocale() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setLocale(request, response, new Locale("nl", ""));

		Cookie cookie = response.getCookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME);
		assertNotNull(cookie);
		assertEquals(CookieLocaleResolver.DEFAULT_COOKIE_NAME, cookie.getName());
		assertEquals(null, cookie.getDomain());
		assertEquals(CookieLocaleResolver.DEFAULT_COOKIE_PATH, cookie.getPath());
		assertFalse(cookie.getSecure());

		request = new MockHttpServletRequest();
		request.setCookies(cookie);

		resolver = new CookieLocaleResolver();
		Locale loc = resolver.resolveLocale(request);
		assertEquals("nl", loc.getLanguage());
	}
