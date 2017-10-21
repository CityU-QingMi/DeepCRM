	@OnWebSocketMessage
	public void onWebSocketBinary(byte[] payload, int offset, int length) {
		BinaryMessage message = new BinaryMessage(payload, offset, length, true);
		try {
			this.webSocketHandler.handleMessage(this.wsSession, message);
		}
		catch (Throwable ex) {
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}
