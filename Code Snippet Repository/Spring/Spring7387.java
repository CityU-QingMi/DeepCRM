	@Test
	public void toMessageWithPayloadAndMutableHeaders() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setHeader("foo", "bar");
		accessor.setLeaveMutable(true);
		MessageHeaders headers = accessor.getMessageHeaders();

		Message<?> message = this.converter.toMessage("payload", headers);

		assertEquals("payload", message.getPayload());
		assertSame(headers, message.getHeaders());
		assertEquals("bar", message.getHeaders().get("foo"));
	}
