	@Override
	public void initialize() {
		// Adapt default cache level.
		if (this.cacheLevel == CACHE_AUTO) {
			this.cacheLevel = (getTransactionManager() != null ? CACHE_NONE : CACHE_CONSUMER);
		}

		// Prepare taskExecutor and maxMessagesPerTask.
		synchronized (this.lifecycleMonitor) {
			if (this.taskExecutor == null) {
				this.taskExecutor = createDefaultTaskExecutor();
			}
			else if (this.taskExecutor instanceof SchedulingTaskExecutor &&
					((SchedulingTaskExecutor) this.taskExecutor).prefersShortLivedTasks() &&
					this.maxMessagesPerTask == Integer.MIN_VALUE) {
				// TaskExecutor indicated a preference for short-lived tasks. According to
				// setMaxMessagesPerTask javadoc, we'll use 10 message per task in this case
				// unless the user specified a custom value.
				this.maxMessagesPerTask = 10;
			}
		}

		// Proceed with actual listener initialization.
		super.initialize();
	}
