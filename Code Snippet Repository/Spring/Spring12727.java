	@Test
	public void testCustomCookie() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("LanguageKoek");
		resolver.setCookieDomain(".springframework.org");
		resolver.setCookiePath("/mypath");
		resolver.setCookieMaxAge(10000);
		resolver.setCookieSecure(true);
		resolver.setLocale(request, response, new Locale("nl", ""));

		Cookie cookie = response.getCookie("LanguageKoek");
		assertNotNull(cookie);
		assertEquals("LanguageKoek", cookie.getName());
		assertEquals(".springframework.org", cookie.getDomain());
		assertEquals("/mypath", cookie.getPath());
		assertEquals(10000, cookie.getMaxAge());
		assertTrue(cookie.getSecure());

		request = new MockHttpServletRequest();
		request.setCookies(cookie);

		resolver = new CookieLocaleResolver();
		resolver.setCookieName("LanguageKoek");
		Locale loc = resolver.resolveLocale(request);
		assertEquals("nl", loc.getLanguage());
	}
