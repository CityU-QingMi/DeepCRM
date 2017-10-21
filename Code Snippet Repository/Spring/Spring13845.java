	@Override
	public void close(CloseStatus status) throws IOException {
		this.closeLock.lock();
		try {
			if (this.closeInProgress) {
				return;
			}
			if (!CloseStatus.SESSION_NOT_RELIABLE.equals(status)) {
				try {
					checkSessionLimits();
				}
				catch (SessionLimitExceededException ex) {
					// Ignore
				}
				if (this.limitExceeded) {
					if (logger.isDebugEnabled()) {
						logger.debug("Changing close status " + status + " to SESSION_NOT_RELIABLE.");
					}
					status = CloseStatus.SESSION_NOT_RELIABLE;
				}
			}
			this.closeInProgress = true;
			super.close(status);
		}
		finally {
			this.closeLock.unlock();
		}
	}
