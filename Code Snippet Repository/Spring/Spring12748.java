	@Test
	public void testSetLocaleToNullLocale() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.GERMAN);
		MockHttpServletResponse response = new MockHttpServletResponse();

		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setLocale(request, response, null);
		Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		assertNull(locale);

		HttpSession session = request.getSession();
		request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.TAIWAN);
		request.setSession(session);
		resolver = new SessionLocaleResolver();
		assertEquals(Locale.TAIWAN, resolver.resolveLocale(request));
	}
