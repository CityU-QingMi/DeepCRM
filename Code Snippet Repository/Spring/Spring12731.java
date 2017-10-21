	@Test
	public void testResolveLocaleContextWithoutCookieAndDefaultLocale() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(Locale.GERMAN);
		resolver.setDefaultTimeZone(TimeZone.getTimeZone("GMT+1"));

		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals(Locale.GERMAN, loc.getLocale());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertEquals(TimeZone.getTimeZone("GMT+1"), ((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
