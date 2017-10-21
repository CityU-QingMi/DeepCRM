	public void shutdown() {
		if (logger.isInfoEnabled()) {
			logger.info("Shutting down ExecutorService" + (this.beanName != null ? " '" + this.beanName + "'" : ""));
		}
		if (this.executor != null) {
			if (this.waitForTasksToCompleteOnShutdown) {
				this.executor.shutdown();
			}
			else {
				this.executor.shutdownNow();
			}
			awaitTerminationIfNecessary(this.executor);
		}
	}
