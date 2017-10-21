	@Test
	public void schedulerWithSpringBeanJobFactory() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		DummyJob.param = 0;
		DummyJob.count = 0;

		JobDetailImpl jobDetail = new JobDetailImpl();
		jobDetail.setDurability(true);
		jobDetail.setJobClass(DummyJob.class);
		jobDetail.setName("myJob");
		jobDetail.getJobDataMap().put("param", "10");
		jobDetail.getJobDataMap().put("ignoredParam", "10");

		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setName("myTrigger");
		trigger.setJobDetail(jobDetail);
		trigger.setStartDelay(1);
		trigger.setRepeatInterval(500);
		trigger.setRepeatCount(1);
		trigger.afterPropertiesSet();

		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setJobFactory(new SpringBeanJobFactory());
		bean.setTriggers(trigger.getObject());
		bean.setJobDetails(jobDetail);
		bean.afterPropertiesSet();
		bean.start();

		Thread.sleep(500);
		assertEquals(10, DummyJob.param);
		assertTrue("DummyJob should have been executed at least once.", DummyJob.count > 0);

		bean.destroy();
	}
