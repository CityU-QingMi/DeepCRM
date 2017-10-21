	@Test
	public void testFixedRepeatedExecutionIsSetUpAndFiresCorrectlyAfterException() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		Runnable runnable = mock(Runnable.class);
		willThrow(new IllegalStateException()).given(runnable).run();

		ScheduledExecutorTask task = new ScheduledExecutorTask(runnable);
		task.setPeriod(500);
		task.setFixedRate(true);

		ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();
		factory.setScheduledExecutorTasks(new ScheduledExecutorTask[]{task});
		factory.setContinueScheduledExecutionAfterException(true);
		factory.afterPropertiesSet();
		pauseToLetTaskStart(2);
		factory.destroy();

		verify(runnable, atLeast(2)).run();
	}
