	@Test
	public void testResolveLocaleWithoutCookieAndDefaultLocale() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(Locale.GERMAN);

		Locale loc = resolver.resolveLocale(request);
		assertEquals(Locale.GERMAN, loc);
	}
