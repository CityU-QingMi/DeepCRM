	@Test
	public void webBindingInitializer() throws Exception {
		RequestMappingHandlerAdapter adapter = this.config.requestMappingHandlerAdapter();

		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) adapter.getWebBindingInitializer();
		assertNotNull(initializer);

		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(null, "");
		initializer.getValidator().validate(null, bindingResult);
		assertEquals("invalid", bindingResult.getAllErrors().get(0).getCode());

		String[] codes = initializer.getMessageCodesResolver().resolveMessageCodes("invalid", null);
		assertEquals("custom.invalid", codes[0]);
	}
