	@Override
	public Receiptable acknowledge(String messageId, boolean consumed) {
		StompHeaders stompHeaders = new StompHeaders();
		if ("1.1".equals(this.version)) {
			stompHeaders.setMessageId(messageId);
		}
		else {
			stompHeaders.setId(messageId);
		}

		String receiptId = checkOrAddReceipt(stompHeaders);
		Receiptable receiptable = new ReceiptHandler(receiptId);

		StompCommand command = (consumed ? StompCommand.ACK : StompCommand.NACK);
		StompHeaderAccessor accessor = createHeaderAccessor(command);
		accessor.addNativeHeaders(stompHeaders);
		Message<byte[]> message = createMessage(accessor, null);
		execute(message);

		return receiptable;
	}
