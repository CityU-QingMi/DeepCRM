	@Test
	public void localeFromResolver() throws Exception {
		Locale locale = Locale.ENGLISH;
		servletRequest.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE,
				new FixedLocaleResolver(locale));

		MethodParameter localeParameter = new MethodParameter(method, 4);
		assertTrue("Locale not supported", resolver.supportsParameter(localeParameter));

		Object result = resolver.resolveArgument(localeParameter, null, webRequest, null);
		assertSame("Invalid result", locale, result);
	}
