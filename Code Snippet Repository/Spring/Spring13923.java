	protected void closeInternal(CloseStatus status) {
		if (this.state == null) {
			logger.warn("Ignoring close since connect() was never invoked");
			return;
		}
		if (isDisconnected()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Ignoring close (already closing or closed): current state " + this.state);
			}
			return;
		}

		this.state = State.CLOSING;
		this.closeStatus = status;
		try {
			disconnect(status);
		}
		catch (Throwable ex) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed to close " + this, ex);
			}
		}
	}
