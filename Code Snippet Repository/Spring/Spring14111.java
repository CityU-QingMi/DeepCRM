	@Test
	public void handleClientMessageProcessingErrorWithReceipt() throws Exception {

		String receiptId = "123";
		StompHeaderAccessor clientHeaderAccessor = StompHeaderAccessor.create(StompCommand.SEND);
		clientHeaderAccessor.setReceipt(receiptId);
		MessageHeaders clientHeaders = clientHeaderAccessor.getMessageHeaders();
		Message<byte[]> clientMessage = MessageBuilder.createMessage(new byte[0], clientHeaders);
		Message<byte[]> actual = this.handler.handleClientMessageProcessingError(clientMessage, new Exception());

		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(actual, StompHeaderAccessor.class);
		assertNotNull(accessor);
		assertEquals(receiptId, accessor.getReceiptId());
	}
