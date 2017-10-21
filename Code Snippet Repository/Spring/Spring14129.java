	@Test
	public void handleMessageToClientWithSimpHeartbeat() {

		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(SimpMessageType.HEARTBEAT);
		accessor.setSessionId("s1");
		accessor.setUser(new TestPrincipal("joe"));
		Message<byte[]> ackMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, ackMessage);

		assertEquals(1, this.session.getSentMessages().size());
		TextMessage actual = (TextMessage) this.session.getSentMessages().get(0);
		assertEquals("\n", actual.getPayload());
	}
