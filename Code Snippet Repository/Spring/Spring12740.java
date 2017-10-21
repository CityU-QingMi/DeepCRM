	@Test
	public void testResolveLocaleContextWithInvalidLocale() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		Cookie cookie = new Cookie("LanguageKoekje", "n-x GMT+1");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("LanguageKoekje");
		try {
			resolver.resolveLocaleContext(request);
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("LanguageKoekje"));
			assertTrue(ex.getMessage().contains("n-x GMT+1"));
		}
	}
