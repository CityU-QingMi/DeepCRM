	@Test
	public void resolveWithPayloadTypeOutOfBound() throws Exception {
		Message<Locale> message = MessageBuilder.withPayload(Locale.getDefault()).build();
		MethodParameter parameter = new MethodParameter(this.method, 3);

		assertTrue(this.resolver.supportsParameter(parameter));
		thrown.expect(MessageConversionException.class);
		thrown.expectMessage(Number.class.getName());
		thrown.expectMessage(Locale.class.getName());
		this.resolver.resolveArgument(parameter, message);
	}
