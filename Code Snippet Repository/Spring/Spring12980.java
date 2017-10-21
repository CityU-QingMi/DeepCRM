	@Test
	public void resolveOptionalRequestPart() throws Exception {
		SimpleBean simpleBean = new SimpleBean("foo");
		given(messageConverter.canRead(SimpleBean.class, MediaType.TEXT_PLAIN)).willReturn(true);
		given(messageConverter.read(eq(SimpleBean.class), isA(HttpInputMessage.class))).willReturn(simpleBean);

		ModelAndViewContainer mavContainer = new ModelAndViewContainer();

		Object actualValue = resolver.resolveArgument(optionalRequestPart, mavContainer, webRequest, new ValidatingBinderFactory());
		assertEquals("Invalid argument value", Optional.of(simpleBean), actualValue);
		assertFalse("The requestHandled flag shouldn't change", mavContainer.isRequestHandled());

		actualValue = resolver.resolveArgument(optionalRequestPart, mavContainer, webRequest, new ValidatingBinderFactory());
		assertEquals("Invalid argument value", Optional.of(simpleBean), actualValue);
		assertFalse("The requestHandled flag shouldn't change", mavContainer.isRequestHandled());
	}
