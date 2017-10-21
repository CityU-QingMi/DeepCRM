	@Test
	public void testSetAndResolveLocaleContextWithTimeZoneOnly() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setLocaleContext(request, response,
				new SimpleTimeZoneAwareLocaleContext(null, TimeZone.getTimeZone("GMT+1")));

		Cookie cookie = response.getCookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME);
		request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.GERMANY);
		request.setCookies(cookie);

		resolver = new CookieLocaleResolver();
		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals(Locale.GERMANY, loc.getLocale());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertEquals(TimeZone.getTimeZone("GMT+1"), ((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
