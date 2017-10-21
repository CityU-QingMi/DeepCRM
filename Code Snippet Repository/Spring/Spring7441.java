	@Test
	public void resolveWithConversion() throws Exception {
		Message<String> message = MessageBuilder.withPayload("test").build();
		MethodParameter parameter = new MethodParameter(this.method, 1);

		when(this.converter.fromMessage(message, Integer.class)).thenReturn(4);

		@SuppressWarnings("unchecked")
		Message<Integer> actual = (Message<Integer>) this.resolver.resolveArgument(parameter, message);

		assertNotNull(actual);
		assertSame(message.getHeaders(), actual.getHeaders());
		assertEquals(new Integer(4), actual.getPayload());
	}
