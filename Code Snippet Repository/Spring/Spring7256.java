	protected ThreadPoolTaskExecutor getTaskExecutor() {
		if (this.corePoolSize != null) {
			this.taskExecutor.setCorePoolSize(this.corePoolSize);
		}
		if (this.maxPoolSize != null) {
			this.taskExecutor.setMaxPoolSize(this.maxPoolSize);
		}
		if (this.keepAliveSeconds != null) {
			this.taskExecutor.setKeepAliveSeconds(this.keepAliveSeconds);
		}
		if (this.queueCapacity != null) {
			this.taskExecutor.setQueueCapacity(this.queueCapacity);
		}
		return this.taskExecutor;
	}
