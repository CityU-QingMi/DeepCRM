	@Test
	public void locale() throws Exception {
		Locale locale = Locale.ENGLISH;
		servletRequest.addPreferredLocale(locale);

		MethodParameter localeParameter = new MethodParameter(method, 4);
		assertTrue("Locale not supported", resolver.supportsParameter(localeParameter));

		Object result = resolver.resolveArgument(localeParameter, null, webRequest, null);
		assertSame("Invalid result", locale, result);
	}
