	@Test
	public void handleMessageToClientWithSimpConnectAck() {

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECT);
		accessor.setHeartbeat(10000, 10000);
		accessor.setAcceptVersion("1.0,1.1");
		Message<?> connectMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());

		SimpMessageHeaderAccessor ackAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.CONNECT_ACK);
		ackAccessor.setHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER, connectMessage);
		ackAccessor.setHeader(SimpMessageHeaderAccessor.HEART_BEAT_HEADER, new long[] {15000, 15000});
		Message<byte[]> ackMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, ackAccessor.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, ackMessage);

		assertEquals(1, this.session.getSentMessages().size());
		TextMessage actual = (TextMessage) this.session.getSentMessages().get(0);
		assertEquals("CONNECTED\n" + "version:1.1\n" + "heart-beat:15000,15000\n" +
				"user-name:joe\n" + "\n" + "\u0000", actual.getPayload());
	}
