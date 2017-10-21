	@Test
	public void testThrowsExceptionIfPoolSizeIsLessThanZero() throws Exception {
		try {
			ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();
			factory.setPoolSize(-1);
			factory.setScheduledExecutorTasks(new ScheduledExecutorTask[]{
				new NoOpScheduledExecutorTask()
			});
			factory.afterPropertiesSet();
			fail("Pool size less than zero");
		}
		catch (IllegalArgumentException expected) {
		}
	}
