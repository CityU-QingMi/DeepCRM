	@Test
	public void schedulerWithSpringBeanJobFactoryAndJobSchedulingData() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);
		DummyJob.param = 0;
		DummyJob.count = 0;

		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setJobFactory(new SpringBeanJobFactory());
		bean.setJobSchedulingDataLocation("org/springframework/scheduling/quartz/job-scheduling-data.xml");
		bean.afterPropertiesSet();
		bean.start();

		Thread.sleep(500);
		assertEquals(10, DummyJob.param);
		assertTrue("DummyJob should have been executed at least once.", DummyJob.count > 0);

		bean.destroy();
	}
