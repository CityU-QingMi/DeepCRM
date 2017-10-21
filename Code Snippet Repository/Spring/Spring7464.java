	@Test
	public void convertAndSendToUser() {
		this.messagingTemplate.convertAndSendToUser("joe", "/queue/foo", "data");
		List<Message<byte[]>> messages = this.messageChannel.getMessages();

		assertEquals(1, messages.size());

		Message<byte[]> message = messages.get(0);
		SimpMessageHeaderAccessor headerAccessor =
				MessageHeaderAccessor.getAccessor(message, SimpMessageHeaderAccessor.class);

		assertNotNull(headerAccessor);
		assertEquals(SimpMessageType.MESSAGE, headerAccessor.getMessageType());
		assertEquals("/user/joe/queue/foo", headerAccessor.getDestination());
	}
