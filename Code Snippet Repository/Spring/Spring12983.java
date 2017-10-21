	private void testResolveArgument(SimpleBean argValue, MethodParameter parameter) throws Exception {
		given(messageConverter.canRead(SimpleBean.class, MediaType.TEXT_PLAIN)).willReturn(true);
		given(messageConverter.read(eq(SimpleBean.class), isA(HttpInputMessage.class))).willReturn(argValue);

		ModelAndViewContainer mavContainer = new ModelAndViewContainer();

		Object actualValue = resolver.resolveArgument(parameter, mavContainer, webRequest, new ValidatingBinderFactory());
		assertEquals("Invalid argument value", argValue, actualValue);
		assertFalse("The requestHandled flag shouldn't change", mavContainer.isRequestHandled());
	}
