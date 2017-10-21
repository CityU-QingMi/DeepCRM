	protected void scheduleHeartbeat() {
		if (this.heartbeatDisabled) {
			return;
		}
		synchronized (this.responseLock) {
			cancelHeartbeat();
			if (!isActive()) {
				return;
			}
			Date time = new Date(System.currentTimeMillis() + this.config.getHeartbeatTime());
			this.heartbeatTask = new HeartbeatTask();
			this.heartbeatFuture = this.config.getTaskScheduler().schedule(this.heartbeatTask, time);
			if (logger.isTraceEnabled()) {
				logger.trace("Scheduled heartbeat in session " + getId());
			}
		}
	}
