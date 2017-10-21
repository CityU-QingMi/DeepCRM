	@Override
	public void handleFailure(Throwable ex) {
		try {
			this.sessionFuture.setException(ex);  // no-op if already set
			this.sessionHandler.handleTransportError(this, ex);
		}
		catch (Throwable ex2) {
			if (logger.isDebugEnabled()) {
				logger.debug("Uncaught failure while handling transport failure", ex2);
			}
		}
	}
