	@Test
	public void testResolveLocaleContextWithoutCookie() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);

		CookieLocaleResolver resolver = new CookieLocaleResolver();

		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals(request.getLocale(), loc.getLocale());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertNull(((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
