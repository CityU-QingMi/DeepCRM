	@Override
	public void afterConnectionClosed() {
		if (logger.isDebugEnabled()) {
			logger.debug("Connection closed in session id=" + this.sessionId);
		}
		if (!this.closing) {
			resetConnection();
			handleFailure(new ConnectionLostException("Connection closed"));
		}
	}
