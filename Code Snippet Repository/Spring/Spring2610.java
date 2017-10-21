	@Override
	public void afterPropertiesSet() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		determinePoolSizeRange(executor);
		if (this.queueCapacity != null) {
			executor.setQueueCapacity(this.queueCapacity);
		}
		if (this.keepAliveSeconds != null) {
			executor.setKeepAliveSeconds(this.keepAliveSeconds);
		}
		if (this.rejectedExecutionHandler != null) {
			executor.setRejectedExecutionHandler(this.rejectedExecutionHandler);
		}
		if (this.beanName != null) {
			executor.setThreadNamePrefix(this.beanName + "-");
		}
		executor.afterPropertiesSet();
		this.target = executor;
	}
