	@Test
	public void stdSchedulerCallsStartingBeforeStartedTest() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.getListenerManager().addSchedulerListener(new TestSchedulerListener());
		sched.start();
		
		Assert.assertEquals(SCHEDULER_STARTING,methodsCalledInSchedulerListener.get(0));
		Assert.assertEquals(SCHEDULER_STARTED,methodsCalledInSchedulerListener.get(1));
		
		sched.shutdown();
	}
