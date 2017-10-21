	@Override
	protected void initializeContainer(DefaultMessageListenerContainer container) {
		if (this.taskExecutor != null) {
			container.setTaskExecutor(this.taskExecutor);
		}
		if (this.transactionManager != null) {
			container.setTransactionManager(this.transactionManager);
		}

		if (this.cacheLevel != null) {
			container.setCacheLevel(this.cacheLevel);
		}
		else if (this.cacheLevelName != null) {
			container.setCacheLevelName(this.cacheLevelName);
		}

		if (this.concurrency != null) {
			container.setConcurrency(this.concurrency);
		}
		if (this.maxMessagesPerTask != null) {
			container.setMaxMessagesPerTask(this.maxMessagesPerTask);
		}
		if (this.receiveTimeout != null) {
			container.setReceiveTimeout(this.receiveTimeout);
		}

		if (this.backOff != null) {
			container.setBackOff(this.backOff);
			if (this.recoveryInterval != null) {
				logger.warn("Ignoring recovery interval in DefaultJmsListenerContainerFactory in favor of BackOff");
			}
		}
		else if (this.recoveryInterval != null) {
			container.setRecoveryInterval(this.recoveryInterval);
		}
	}
