	@Test
	public void testResolveLocaleContextWithInvalidTimeZone() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		Cookie cookie = new Cookie("LanguageKoekje", "nl X-MT");
		request.setCookies(cookie);

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("LanguageKoekje");
		try {
			resolver.resolveLocaleContext(request);
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("LanguageKoekje"));
			assertTrue(ex.getMessage().contains("nl X-MT"));
		}
	}
