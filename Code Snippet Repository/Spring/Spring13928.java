	public void handleTransportError(Throwable error) {
		try {
			if (logger.isErrorEnabled()) {
				logger.error("Transport error in " + this, error);
			}
			this.webSocketHandler.handleTransportError(this, error);
		}
		catch (Throwable ex) {
			logger.error("WebSocketHandler.handleTransportError threw an exception", ex);
		}
	}
