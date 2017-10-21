	@Test
	public void testOneTimeExecutionIsSetUpAndFiresCorrectly() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		Runnable runnable = mock(Runnable.class);

		ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();
		factory.setScheduledExecutorTasks(new ScheduledExecutorTask[]{
			new ScheduledExecutorTask(runnable)
		});
		factory.afterPropertiesSet();
		pauseToLetTaskStart(1);
		factory.destroy();

		verify(runnable).run();
	}
