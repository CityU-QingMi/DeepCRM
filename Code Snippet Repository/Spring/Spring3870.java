		@Bean
		public TaskScheduler scheduler() {
			ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
			scheduler.initialize();
			scheduler.schedule(
				new Runnable() {
					@Override
					public void run() {
						counter().incrementAndGet();
					}
				},
				new Trigger() {
					@Override
					public Date nextExecutionTime(TriggerContext triggerContext) {
						return new Date(new Date().getTime()+10);
					}
				});
			return scheduler;
		}
