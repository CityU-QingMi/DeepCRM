	@Test
	public void handleMessageToClientWithSimpDisconnectAckAndReceipt() {

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.DISCONNECT);
		accessor.setReceipt("message-123");
		Message<?> connectMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());

		SimpMessageHeaderAccessor ackAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.DISCONNECT_ACK);
		ackAccessor.setHeader(SimpMessageHeaderAccessor.DISCONNECT_MESSAGE_HEADER, connectMessage);
		Message<byte[]> ackMessage = MessageBuilder.createMessage(EMPTY_PAYLOAD, ackAccessor.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(this.session, ackMessage);

		assertEquals(1, this.session.getSentMessages().size());
		TextMessage actual = (TextMessage) this.session.getSentMessages().get(0);
		assertEquals("RECEIPT\n" + "receipt-id:message-123\n" + "\n\u0000", actual.getPayload());
	}
