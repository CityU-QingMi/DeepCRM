	@Test
	public void testSetLocaleContextToNullWithDefault() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);
		Cookie cookie = new Cookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME, Locale.UK.toString());
		request.setCookies(cookie);
		MockHttpServletResponse response = new MockHttpServletResponse();

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(Locale.CANADA_FRENCH);
		resolver.setDefaultTimeZone(TimeZone.getTimeZone("GMT+1"));
		resolver.setLocaleContext(request, response, null);
		Locale locale = (Locale) request.getAttribute(CookieLocaleResolver.LOCALE_REQUEST_ATTRIBUTE_NAME);
		assertEquals(Locale.CANADA_FRENCH, locale);
		TimeZone timeZone = (TimeZone) request.getAttribute(CookieLocaleResolver.TIME_ZONE_REQUEST_ATTRIBUTE_NAME);
		assertEquals(TimeZone.getTimeZone("GMT+1"), timeZone);

		Cookie[] cookies = response.getCookies();
		assertEquals(1, cookies.length);
		Cookie localeCookie = cookies[0];
		assertEquals(CookieLocaleResolver.DEFAULT_COOKIE_NAME, localeCookie.getName());
		assertEquals("", localeCookie.getValue());
	}
