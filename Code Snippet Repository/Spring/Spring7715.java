	@Test
	public void leaveMutableDefaultBehavior() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setHeader("foo", "bar");
		MessageHeaders headers = accessor.getMessageHeaders();
		Message<?> message = MessageBuilder.createMessage("payload", headers);

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Already immutable");
		accessor.setLeaveMutable(true);

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage("Already immutable");
		accessor.setHeader("foo", "baz");

		assertEquals("bar", headers.get("foo"));
		assertSame(accessor, MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class));
	}
