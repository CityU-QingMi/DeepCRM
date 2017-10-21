	public void afterTransportClosed(@Nullable CloseStatus closeStatus) {
		CloseStatus cs = this.closeStatus;
		if (cs == null) {
			cs = closeStatus;
			this.closeStatus = closeStatus;
		}
		Assert.state(cs != null, "CloseStatus not available");
		if (logger.isDebugEnabled()) {
			logger.debug("Transport closed with " + cs + " in " + this);
		}

		this.state = State.CLOSED;
		try {
			this.webSocketHandler.afterConnectionClosed(this, cs);
		}
		catch (Throwable ex) {
			logger.error("WebSocketHandler.afterConnectionClosed threw an exception", ex);
		}
	}
