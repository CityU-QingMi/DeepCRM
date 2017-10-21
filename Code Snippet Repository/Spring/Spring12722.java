	@Test
	public void testResolveLocale() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		Cookie cookie = new Cookie("LanguageKoekje", "nl");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("LanguageKoekje");
		Locale loc = resolver.resolveLocale(request);
		assertEquals(loc.getLanguage(), "nl");
	}
