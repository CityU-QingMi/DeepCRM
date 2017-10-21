	@Test
	public void testResolveLocaleContextWithInvalidLocaleOnErrorDispatch() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.GERMAN);
		request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, new ServletException());
		Cookie cookie = new Cookie("LanguageKoekje", "n-x GMT+1");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultTimeZone(TimeZone.getTimeZone("GMT+2"));
		resolver.setCookieName("LanguageKoekje");
		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals(Locale.GERMAN, loc.getLocale());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertEquals(TimeZone.getTimeZone("GMT+2"), ((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
