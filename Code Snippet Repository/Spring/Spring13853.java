	private void handleError(WebSocketSession session, Throwable ex, @Nullable Message<byte[]> clientMessage) {
		if (getErrorHandler() == null) {
			sendErrorMessage(session, ex);
			return;
		}

		Message<byte[]> message = getErrorHandler().handleClientMessageProcessingError(clientMessage, ex);
		if (message == null) {
			return;
		}

		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		Assert.state(accessor != null, "No StompHeaderAccessor");
		sendToClient(session, accessor, message.getPayload());
	}
