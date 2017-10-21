	@Override
	public void onClose(javax.websocket.Session session, CloseReason reason) {
		CloseStatus closeStatus = new CloseStatus(reason.getCloseCode().getCode(), reason.getReasonPhrase());
		try {
			this.handler.afterConnectionClosed(this.wsSession, closeStatus);
		}
		catch (Throwable ex) {
			if (logger.isErrorEnabled()) {
				logger.error("Unhandled error for " + this.wsSession, ex);
			}
		}
	}
