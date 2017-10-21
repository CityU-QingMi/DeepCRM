	@Test
	public void testSetAndResolveLocale() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setLocale(request, response, Locale.GERMAN);
		assertEquals(Locale.GERMAN, resolver.resolveLocale(request));

		HttpSession session = request.getSession();
		request = new MockHttpServletRequest();
		request.setSession(session);
		resolver = new SessionLocaleResolver();

		assertEquals(Locale.GERMAN, resolver.resolveLocale(request));
	}
