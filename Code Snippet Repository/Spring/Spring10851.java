	@Test
	@SuppressWarnings("")
	public void dateConversion() throws Exception {
		String rfc1123val = "Thu, 21 Apr 2016 17:11:08 +0100";
		servletRequest.addHeader("name", rfc1123val);

		ConfigurableWebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();
		bindingInitializer.setConversionService(new DefaultFormattingConversionService());
		Object result = resolver.resolveArgument(paramDate, null, webRequest,
				new DefaultDataBinderFactory(bindingInitializer));

		assertTrue(result instanceof Date);
		assertEquals(new Date(rfc1123val), result);
	}
