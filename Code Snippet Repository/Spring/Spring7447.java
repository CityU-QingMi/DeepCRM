	@Test
	public void resolveWithConversionNeededButNoConverter() throws Exception {
		this.resolver = new MessageMethodArgumentResolver();

		Message<String> message = MessageBuilder.withPayload("test").build();
		MethodParameter parameter = new MethodParameter(this.method, 1);

		assertTrue(this.resolver.supportsParameter(parameter));
		thrown.expect(MessageConversionException.class);
		thrown.expectMessage(Integer.class.getName());
		thrown.expectMessage(String.class.getName());
		this.resolver.resolveArgument(parameter, message);
	}
