	@Test
	@SuppressWarnings("")
	public void testShutdownIsPropagatedToTheExecutorOnDestroy() throws Exception {
		final ScheduledExecutorService executor = mock(ScheduledExecutorService.class);

		ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean() {
			@Override
			protected ScheduledExecutorService createExecutor(int poolSize, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
				return executor;
			}
		};
		factory.setScheduledExecutorTasks(new ScheduledExecutorTask[]{
			new NoOpScheduledExecutorTask()
		});
		factory.setWaitForTasksToCompleteOnShutdown(true);
		factory.afterPropertiesSet();
		factory.destroy();

		verify(executor).shutdown();
	}
