	@Test
	public void destroyLazyInitSchedulerWithCustomShutdownOrderDoesNotHang() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("quartzSchedulerLifecycleTests.xml", this.getClass());
		assertNotNull(context.getBean("lazyInitSchedulerWithCustomShutdownOrder"));
		StopWatch sw = new StopWatch();
		sw.start("lazyScheduler");
		context.close();
		sw.stop();
		assertTrue("Quartz Scheduler with lazy-init is hanging on destruction: " +
				sw.getTotalTimeMillis(), sw.getTotalTimeMillis() < 500);
	}
