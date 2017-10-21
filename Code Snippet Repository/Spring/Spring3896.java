	@Ignore
	@Test
	public void testWithInitialDelayRepeatedExecutionIsSetUpAndFiresCorrectlyAfterException() throws Exception {
		Runnable runnable = mock(Runnable.class);
		willThrow(new IllegalStateException()).given(runnable).run();

		ScheduledExecutorTask task = new ScheduledExecutorTask(runnable);
		task.setPeriod(500);
		task.setDelay(3000); // nice long wait...

		ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();
		factory.setScheduledExecutorTasks(new ScheduledExecutorTask[] {task});
		factory.setContinueScheduledExecutionAfterException(true);
		factory.afterPropertiesSet();
		pauseToLetTaskStart(1);
		// invoke destroy before tasks have even been scheduled...
		factory.destroy();

		// Mock must never have been called
		verify(runnable, never()).run();
	}
