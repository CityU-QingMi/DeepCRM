	private TaskScheduler initTaskScheduler() {
		if (this.scheduler == null) {
			ServletWebSocketHandlerRegistry registry = initHandlerRegistry();
			if (registry.requiresTaskScheduler()) {
				ThreadPoolTaskScheduler threadPoolScheduler = new ThreadPoolTaskScheduler();
				threadPoolScheduler.setThreadNamePrefix("SockJS-");
				threadPoolScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
				threadPoolScheduler.setRemoveOnCancelPolicy(true);
				this.scheduler = threadPoolScheduler;
			}
			else {
				this.scheduler = new NoOpScheduler();
			}
		}
		return scheduler;
	}
