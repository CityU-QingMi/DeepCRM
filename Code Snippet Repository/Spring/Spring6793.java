	protected void scheduleNewInvokerIfAppropriate() {
		if (isRunning()) {
			resumePausedTasks();
			synchronized (this.lifecycleMonitor) {
				if (this.scheduledInvokers.size() < this.maxConcurrentConsumers &&
						getIdleInvokerCount() < this.idleConsumerLimit) {
					scheduleNewInvoker();
					if (logger.isDebugEnabled()) {
						logger.debug("Raised scheduled invoker count: " + this.scheduledInvokers.size());
					}
				}
			}
		}
	}
