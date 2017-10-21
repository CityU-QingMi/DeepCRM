	@OnWebSocketConnect
	public void onWebSocketConnect(Session session) {
		try {
			this.wsSession.initializeNativeSession(session);
			this.webSocketHandler.afterConnectionEstablished(this.wsSession);
		}
		catch (Throwable ex) {
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}
