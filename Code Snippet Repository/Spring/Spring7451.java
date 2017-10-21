	@Test
	public void resolveNotRequired() throws Exception {
		Message<?> emptyByteArrayMessage = MessageBuilder.withPayload(new byte[0]).build();
		assertNull(this.resolver.resolveArgument(this.paramAnnotatedNotRequired, emptyByteArrayMessage));

		Message<?> emptyStringMessage = MessageBuilder.withPayload("").build();
		assertNull(this.resolver.resolveArgument(this.paramAnnotatedNotRequired, emptyStringMessage));

		Message<?> notEmptyMessage = MessageBuilder.withPayload("ABC".getBytes()).build();
		assertEquals("ABC", this.resolver.resolveArgument(this.paramAnnotatedNotRequired, notEmptyMessage));
	}
