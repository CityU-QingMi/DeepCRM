	@Test
	public void resolveWithPayloadTypeAsWildcardAndNoConverter() throws Exception {
		this.resolver = new MessageMethodArgumentResolver();

		Message<String> message = MessageBuilder.withPayload("test").build();
		MethodParameter parameter = new MethodParameter(this.method, 0);

		assertTrue(this.resolver.supportsParameter(parameter));
		assertSame(message, this.resolver.resolveArgument(parameter, message));
	}
