	@Test
	public void defaultLocale() throws Exception {
		this.resolver.setDefaultLocale(JAPANESE);
		MockHttpServletRequest request = new MockHttpServletRequest();
		assertEquals(JAPANESE, this.resolver.resolveLocale(request));

		request.addHeader("Accept-Language", US.toLanguageTag());
		request.setPreferredLocales(Collections.singletonList(US));
		assertEquals(US, this.resolver.resolveLocale(request));
	}
