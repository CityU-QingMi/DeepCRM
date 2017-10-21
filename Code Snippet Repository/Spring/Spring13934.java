	private void scheduleConnectTimeoutTask(ConnectCallback connectHandler) {
		if (this.timeoutScheduler != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Scheduling connect to time out after " + this.timeoutValue + " ms.");
			}
			Date timeoutDate = new Date(System.currentTimeMillis() + this.timeoutValue);
			this.timeoutScheduler.schedule(connectHandler, timeoutDate);
		}
		else if (logger.isTraceEnabled()) {
			logger.trace("Connect timeout task not scheduled (no TaskScheduler configured).");
		}
	}
