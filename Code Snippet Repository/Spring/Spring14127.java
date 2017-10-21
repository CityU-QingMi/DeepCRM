	@Test
	public void handleMessageToClientWithSimpDisconnectAck() {

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.DISCONNECT);
		Message<?> connectMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());

		SimpMessageHeaderAccessor ackAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.DISCONNECT_ACK);
		ackAccessor.setHeader(SimpMessageHeaderAccessor.DISCONNECT_MESSAGE_HEADER, connectMessage);
		Message<byte[]> ackMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, ackAccessor.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, ackMessage);

		assertEquals(1, this.session.getSentMessages().size());
		TextMessage actual = (TextMessage) this.session.getSentMessages().get(0);
		assertEquals("ERROR\n" + "message:Session closed.\n" + "content-length:0\n" +
				"\n\u0000", actual.getPayload());
	}
