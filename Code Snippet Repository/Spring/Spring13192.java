	@Test
	public void timeZoneFromResolver() throws Exception {
		TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
		servletRequest.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE,
				new FixedLocaleResolver(Locale.US, timeZone));

		MethodParameter timeZoneParameter = new MethodParameter(method, 8);
		assertTrue("TimeZone not supported", resolver.supportsParameter(timeZoneParameter));

		Object result = resolver.resolveArgument(timeZoneParameter, null, webRequest, null);
		assertEquals("Invalid result", timeZone, result);
	}
