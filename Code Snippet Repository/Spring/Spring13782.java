	@OnWebSocketMessage
	public void onWebSocketText(String payload) {
		TextMessage message = new TextMessage(payload);
		try {
			this.webSocketHandler.handleMessage(this.wsSession, message);
		}
		catch (Throwable ex) {
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}
