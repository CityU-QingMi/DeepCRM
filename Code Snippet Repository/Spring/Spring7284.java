		protected void handleTcpConnectionFailure(String error, @Nullable Throwable ex) {
			if (logger.isErrorEnabled()) {
				logger.error("TCP connection failure in session " + this.sessionId + ": " + error, ex);
			}
			try {
				sendStompErrorFrameToClient(error);
			}
			finally {
				try {
					clearConnection();
				}
				catch (Throwable ex2) {
					if (logger.isDebugEnabled()) {
						logger.debug("Failure while clearing TCP connection state in session " + this.sessionId, ex2);
					}
				}
			}
		}
