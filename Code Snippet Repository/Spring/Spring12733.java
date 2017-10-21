	@Test
	public void testResolveLocaleContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		Cookie cookie = new Cookie("LanguageKoekje", "nl");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("LanguageKoekje");
		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals("nl", loc.getLocale().getLanguage());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertNull(((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
