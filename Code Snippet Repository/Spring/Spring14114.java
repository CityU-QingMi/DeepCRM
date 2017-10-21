	@Test
	public void handleMessageToClientWithBinaryWebSocketMessage() {

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.MESSAGE);
		headers.setMessageId("mess0");
		headers.setSubscriptionId("sub0");
		headers.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM);
		headers.setDestination("/queue/foo");

		// Non-empty payload

		byte[] payload = new byte[1];
		Message<byte[]> message = MessageBuilder.createMessage(payload, headers.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, message);

		assertEquals(1, this.session.getSentMessages().size());
		WebSocketMessage<?> webSocketMessage = this.session.getSentMessages().get(0);
		assertTrue(webSocketMessage instanceof BinaryMessage);

		// Empty payload

		payload = EMPTY_PAYLOAD;
		message = MessageBuilder.createMessage(payload, headers.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, message);

		assertEquals(2, this.session.getSentMessages().size());
		webSocketMessage = this.session.getSentMessages().get(1);
		assertTrue(webSocketMessage instanceof TextMessage);
	}
