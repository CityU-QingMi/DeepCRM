		private void afterDisconnectSent(StompHeaderAccessor accessor) {
			if (accessor.getReceipt() == null) {
				try {
					clearConnection();
				}
				catch (Throwable ex) {
					if (logger.isDebugEnabled()) {
						logger.debug("Failure while clearing TCP connection state in session " + this.sessionId, ex);
					}
				}
			}
		}
