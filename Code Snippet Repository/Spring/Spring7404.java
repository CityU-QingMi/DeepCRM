	@Test
	public void convertAndSendWithSimpMessageHeaders() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setHeader("key", "value");
		accessor.setLeaveMutable(true);
		MessageHeaders headers = accessor.getMessageHeaders();

		this.template.convertAndSend("channel", "data", headers);
		List<Message<byte[]>> messages = this.messageChannel.getMessages();
		Message<byte[]> message = messages.get(0);

		assertSame(headers, message.getHeaders());
		assertFalse(accessor.isMutable());
	}
