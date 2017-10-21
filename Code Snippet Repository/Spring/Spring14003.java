	protected void cancelHeartbeat() {
		synchronized (this.responseLock) {
			if (this.heartbeatFuture != null) {
				if (logger.isTraceEnabled()) {
					logger.trace("Cancelling heartbeat in session " + getId());
				}
				this.heartbeatFuture.cancel(false);
				this.heartbeatFuture = null;
			}
			if (this.heartbeatTask != null) {
				this.heartbeatTask.cancel();
				this.heartbeatTask = null;
			}
		}
	}
