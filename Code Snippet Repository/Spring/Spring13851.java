	@Override
	@Nullable
	public Message<byte[]> handleClientMessageProcessingError(@Nullable Message<byte[]> clientMessage, Throwable ex) {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
		accessor.setMessage(ex.getMessage());
		accessor.setLeaveMutable(true);

		StompHeaderAccessor clientHeaderAccessor = null;
		if (clientMessage != null) {
			clientHeaderAccessor = MessageHeaderAccessor.getAccessor(clientMessage, StompHeaderAccessor.class);
			if (clientHeaderAccessor != null) {
				String receiptId = clientHeaderAccessor.getReceipt();
				if (receiptId != null) {
					accessor.setReceiptId(receiptId);
				}
			}
		}

		return handleInternal(accessor, EMPTY_PAYLOAD, ex, clientHeaderAccessor);
	}
