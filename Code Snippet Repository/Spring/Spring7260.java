	@Override
	public Receiptable send(StompHeaders stompHeaders, Object payload) {
		Assert.hasText(stompHeaders.getDestination(), "Destination header is required");

		String receiptId = checkOrAddReceipt(stompHeaders);
		Receiptable receiptable = new ReceiptHandler(receiptId);

		StompHeaderAccessor accessor = createHeaderAccessor(StompCommand.SEND);
		accessor.addNativeHeaders(stompHeaders);
		Message<byte[]> message = createMessage(accessor, payload);
		execute(message);

		return receiptable;
	}
