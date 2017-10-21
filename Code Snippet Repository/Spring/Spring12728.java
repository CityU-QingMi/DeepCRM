	@Test
	public void testResolveLocaleWithoutCookie() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);

		CookieLocaleResolver resolver = new CookieLocaleResolver();

		Locale loc = resolver.resolveLocale(request);
		assertEquals(request.getLocale(), loc);
	}
