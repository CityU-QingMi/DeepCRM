	@Test
	public void testResolveLocaleWithoutSessionAndDefaultLocale() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);

		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.GERMAN);

		assertEquals(Locale.GERMAN, resolver.resolveLocale(request));
	}
