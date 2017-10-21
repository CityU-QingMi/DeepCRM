	@Test
	public void testResolveLocaleContextWithCookieWithoutLocale() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);
		Cookie cookie = new Cookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME, "");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();

		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals(request.getLocale(), loc.getLocale());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertNull(((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
