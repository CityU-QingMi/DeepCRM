	@Test
	public void doSendWithStompHeaders() {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
		accessor.setDestination("/user/queue/foo");
		Message<?> message = MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());

		this.messagingTemplate.doSend("/queue/foo-user123", message);

		List<Message<byte[]>> messages = this.messageChannel.getMessages();
		Message<byte[]> sentMessage = messages.get(0);

		MessageHeaderAccessor sentAccessor = MessageHeaderAccessor.getAccessor(sentMessage, MessageHeaderAccessor.class);
		assertEquals(StompHeaderAccessor.class, sentAccessor.getClass());
		assertEquals("/queue/foo-user123", ((StompHeaderAccessor) sentAccessor).getDestination());
	}
