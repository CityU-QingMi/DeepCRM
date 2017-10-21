	@Test
	public void handleMessageToClientWithUserDestination() {

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.MESSAGE);
		headers.setMessageId("mess0");
		headers.setSubscriptionId("sub0");
		headers.setDestination("/queue/foo-user123");
		headers.setNativeHeader(StompHeaderAccessor.ORIGINAL_DESTINATION, "/user/queue/foo");
		Message<byte[]> message = MessageBuilder.createMessage(EMPTY_PAYLOAD, headers.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, message);

		assertEquals(1, this.session.getSentMessages().size());
		WebSocketMessage<?> textMessage = this.session.getSentMessages().get(0);
		assertTrue(((String) textMessage.getPayload()).contains("destination:/user/queue/foo\n"));
		assertFalse(((String) textMessage.getPayload()).contains(SimpMessageHeaderAccessor.ORIGINAL_DESTINATION));
	}
