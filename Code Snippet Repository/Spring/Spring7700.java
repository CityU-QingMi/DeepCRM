	@Test
	public void testBuildMessageWithDefaultMutability() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		MessageHeaders headers = accessor.getMessageHeaders();
		Message<?> message = MessageBuilder.createMessage("foo", headers);

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Already immutable");
		accessor.setHeader("foo", "bar");

		assertSame(accessor, MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class));
	}
