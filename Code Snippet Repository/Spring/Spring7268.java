		@Override
		public void run() {
			closing = true;
			String error = "Server has gone quite. Closing connection in session id=" + sessionId + ".";
			if (logger.isDebugEnabled()) {
				logger.debug(error);
			}
			resetConnection();
			handleFailure(new IllegalStateException(error));
		}
