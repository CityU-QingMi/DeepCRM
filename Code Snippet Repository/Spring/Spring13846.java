	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
		try {
			getDelegate().afterConnectionClosed(session, closeStatus);
		}
		catch (Throwable ex) {
			if (logger.isErrorEnabled()) {
				logger.error("Unhandled error for " + this, ex);
			}
		}
	}
