	@Test
	public void zoneIdFromResolver() throws Exception {
		TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
		servletRequest.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE,
				new FixedLocaleResolver(Locale.US, timeZone));
		MethodParameter zoneIdParameter = new MethodParameter(method, 9);

		assertTrue("ZoneId not supported", resolver.supportsParameter(zoneIdParameter));

		Object result = resolver.resolveArgument(zoneIdParameter, null, webRequest, null);
		assertEquals("Invalid result", timeZone.toZoneId(), result);
	}
