	@Test
	public void testFixedRepeatedExecutionIsSetUpAndFiresCorrectly() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		Runnable runnable = mock(Runnable.class);

		ScheduledExecutorTask task = new ScheduledExecutorTask(runnable);
		task.setPeriod(500);
		task.setFixedRate(true);

		ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();
		factory.setScheduledExecutorTasks(new ScheduledExecutorTask[]{task});
		factory.afterPropertiesSet();
		pauseToLetTaskStart(2);
		factory.destroy();

		verify(runnable, atLeast(2)).run();
	}
