		@Override
		public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
			taskRegistrar.setScheduler(taskScheduler());
			taskRegistrar.addFixedRateTask(new IntervalTask(
					new Runnable() {
						@Override
						public void run() {
							worker().executedByThread = Thread.currentThread().getName();
						}
					},
					10, 0));
		}
