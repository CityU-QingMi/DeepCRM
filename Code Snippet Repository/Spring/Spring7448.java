	@Test
	public void resolveWithConversionEmptyPayloadButNoConverter() throws Exception {
		this.resolver = new MessageMethodArgumentResolver();

		Message<String> message = MessageBuilder.withPayload("").build();
		MethodParameter parameter = new MethodParameter(this.method, 1);

		assertTrue(this.resolver.supportsParameter(parameter));
		thrown.expect(MessageConversionException.class);
		thrown.expectMessage("payload is empty");
		thrown.expectMessage(Integer.class.getName());
		thrown.expectMessage(String.class.getName());
		this.resolver.resolveArgument(parameter, message);
	}
