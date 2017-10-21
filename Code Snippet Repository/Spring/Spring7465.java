	@Test
	public void convertAndSendToUserWithEncoding() {
		this.messagingTemplate.convertAndSendToUser("http://joe.openid.example.org/", "/queue/foo", "data");
		List<Message<byte[]>> messages = this.messageChannel.getMessages();

		assertEquals(1, messages.size());

		SimpMessageHeaderAccessor headerAccessor =
				MessageHeaderAccessor.getAccessor(messages.get(0), SimpMessageHeaderAccessor.class);

		assertNotNull(headerAccessor);
		assertEquals("/user/http:%2F%2Fjoe.openid.example.org%2F/queue/foo", headerAccessor.getDestination());
	}
