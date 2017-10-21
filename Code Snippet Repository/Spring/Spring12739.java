	@Test
	public void testResolveLocaleContextWithTimeZone() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		Cookie cookie = new Cookie("LanguageKoekje", "nl GMT+1");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("LanguageKoekje");
		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals("nl", loc.getLocale().getLanguage());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertEquals(TimeZone.getTimeZone("GMT+1"), ((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
