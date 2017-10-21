	@Test
	public void handleMessageToClientWithDestinationUserNameProvider() {

		this.session.setPrincipal(new UniqueUser("joe"));

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.CONNECTED);
		Message<byte[]> message = MessageBuilder.createMessage(EMPTY_PAYLOAD, headers.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, message);

		assertEquals(1, this.session.getSentMessages().size());
		WebSocketMessage<?> textMessage = this.session.getSentMessages().get(0);
		assertEquals("CONNECTED\n" + "user-name:joe\n" + "\n" + "\u0000", textMessage.getPayload());
	}
