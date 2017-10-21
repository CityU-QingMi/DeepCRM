	@Test
	public void testSetAndResolveLocaleWithCountryAsLanguageTag() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setLanguageTagCompliant(true);
		resolver.setLocale(request, response, new Locale("de", "AT"));

		Cookie cookie = response.getCookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME);
		assertNotNull(cookie);
		assertEquals(CookieLocaleResolver.DEFAULT_COOKIE_NAME, cookie.getName());
		assertEquals(null, cookie.getDomain());
		assertEquals(CookieLocaleResolver.DEFAULT_COOKIE_PATH, cookie.getPath());
		assertFalse(cookie.getSecure());
		assertEquals("de-AT", cookie.getValue());

		request = new MockHttpServletRequest();
		request.setCookies(cookie);

		resolver = new CookieLocaleResolver();
		resolver.setLanguageTagCompliant(true);
		Locale loc = resolver.resolveLocale(request);
		assertEquals("de", loc.getLanguage());
		assertEquals("AT", loc.getCountry());
	}
