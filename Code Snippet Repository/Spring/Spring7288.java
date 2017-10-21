		@Override
		public void afterConnectionClosed() {
			if (this.tcpConnection == null) {
				return;
			}
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("TCP connection to broker closed in session " + this.sessionId);
				}
				sendStompErrorFrameToClient("Connection to broker closed.");
			}
			finally {
				try {
					// Prevent clearConnection() from trying to close
					this.tcpConnection = null;
					clearConnection();
				}
				catch (Throwable ex) {
					// Shouldn't happen with connection reset beforehand
				}
			}
		}
