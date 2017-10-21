	@Test
	public void broadcastSchedulerListenerCallsSchedulerStartingOnAllItsListeners() throws SchedulerException {
		
		methodsCalledInSchedulerListener =  new ArrayList<String>();
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		List<SchedulerListener> listeners=  new ArrayList<SchedulerListener>();
		listeners.add(new TestSchedulerListener());
		
		sched.getListenerManager().addSchedulerListener(new BroadcastSchedulerListener(listeners));
		sched.start();
		
		Assert.assertEquals(SCHEDULER_STARTING,methodsCalledInSchedulerListener.get(0));
		Assert.assertEquals(SCHEDULER_STARTED,methodsCalledInSchedulerListener.get(1));
		
		sched.shutdown();
	}
