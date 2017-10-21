	@Test
	public void testSetLocaleToNullWithDefault() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);
		Cookie cookie = new Cookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME, Locale.UK.toString());
		request.setCookies(cookie);
		MockHttpServletResponse response = new MockHttpServletResponse();

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(Locale.CANADA_FRENCH);
		resolver.setLocale(request, response, null);
		Locale locale = (Locale) request.getAttribute(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME);
		assertEquals(Locale.CANADA_FRENCH, locale);

		Cookie[] cookies = response.getCookies();
		assertEquals(1, cookies.length);
		Cookie localeCookie = cookies[0];
		assertEquals(CookieLocaleResolver.DEFAULT_COOKIE_NAME, localeCookie.getName());
		assertEquals("", localeCookie.getValue());
	}
