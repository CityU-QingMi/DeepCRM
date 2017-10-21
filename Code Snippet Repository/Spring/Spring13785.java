	@OnWebSocketClose
	public void onWebSocketClose(int statusCode, String reason) {
		CloseStatus closeStatus = new CloseStatus(statusCode, reason);
		try {
			this.webSocketHandler.afterConnectionClosed(this.wsSession, closeStatus);
		}
		catch (Throwable ex) {
			if (logger.isErrorEnabled()) {
				logger.error("Unhandled error for " + this.wsSession, ex);
			}
		}
	}
