	@Test
	public void testSetAndResolveLocaleContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setLocaleContext(request, response, new SimpleLocaleContext(new Locale("nl", "")));

		Cookie cookie = response.getCookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME);
		request = new MockHttpServletRequest();
		request.setCookies(cookie);

		resolver = new CookieLocaleResolver();
		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals("nl", loc.getLocale().getLanguage());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertNull(((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
