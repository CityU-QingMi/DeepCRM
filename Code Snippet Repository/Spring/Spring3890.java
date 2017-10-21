	@Test
	@SuppressWarnings("")
	public void testShutdownNowIsPropagatedToTheExecutorOnDestroy() throws Exception {
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
		factory.afterPropertiesSet();
		factory.destroy();

		verify(executor).shutdownNow();
	}
