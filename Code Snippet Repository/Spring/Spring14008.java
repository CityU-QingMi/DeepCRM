	public void tryCloseWithSockJsTransportError(Throwable error, CloseStatus closeStatus) {
		if (logger.isDebugEnabled()) {
			logger.debug("Closing due to transport error for " + this);
		}
		try {
			delegateError(error);
		}
		catch (Throwable delegateException) {
			// Ignore
			logger.debug("Exception from error handling delegate", delegateException);
		}
		try {
			close(closeStatus);
		}
		catch (Throwable closeException) {
			logger.debug("Failure while closing " + this, closeException);
		}
	}
