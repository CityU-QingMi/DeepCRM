	@Test
	public void instantConversion() throws Exception {
		String rfc1123val = "Thu, 21 Apr 2016 17:11:08 +0100";
		servletRequest.addHeader("name", rfc1123val);

		ConfigurableWebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();
		bindingInitializer.setConversionService(new DefaultFormattingConversionService());
		Object result = resolver.resolveArgument(paramInstant, null, webRequest,
				new DefaultDataBinderFactory(bindingInitializer));

		assertTrue(result instanceof Instant);
		assertEquals(Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(rfc1123val)), result);
	}
