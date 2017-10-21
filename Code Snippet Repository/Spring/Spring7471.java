	@Test
	public void doSendWithMutableHeaders() {
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
		accessor.setHeader("key", "value");
		accessor.setNativeHeader("fooNative", "barNative");
		accessor.setLeaveMutable(true);
		MessageHeaders headers = accessor.getMessageHeaders();
		Message<?> message = MessageBuilder.createMessage("payload", headers);

		this.messagingTemplate.doSend("/topic/foo", message);

		List<Message<byte[]>> messages = this.messageChannel.getMessages();
		Message<byte[]> sentMessage = messages.get(0);

		assertSame(message, sentMessage);
		assertFalse(accessor.isMutable());
	}
