	@Test
	public void testResolveLocaleWithCookieWithoutLocale() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);
		Cookie cookie = new Cookie(CookieLocaleResolver.DEFAULT_COOKIE_NAME, "");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();

		Locale loc = resolver.resolveLocale(request);
		assertEquals(request.getLocale(), loc);
	}
