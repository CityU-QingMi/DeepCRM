	@Test
	public void testResolveLocaleContextWithInvalidTimeZoneOnErrorDispatch() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, new ServletException());
		Cookie cookie = new Cookie("LanguageKoekje", "nl X-MT");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultTimeZone(TimeZone.getTimeZone("GMT+2"));
		resolver.setCookieName("LanguageKoekje");
		LocaleContext loc = resolver.resolveLocaleContext(request);
		assertEquals("nl", loc.getLocale().getLanguage());
		assertTrue(loc instanceof TimeZoneAwareLocaleContext);
		assertEquals(TimeZone.getTimeZone("GMT+2"), ((TimeZoneAwareLocaleContext) loc).getTimeZone());
	}
