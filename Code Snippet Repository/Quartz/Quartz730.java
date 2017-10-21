	@Test
	public void testTriggerFinalized() throws Exception {
		Qtz205TriggerListener triggerListener = new Qtz205TriggerListener();
		Qtz205ScheListener schedulerListener = new Qtz205ScheListener();
		Properties props = new Properties();
		props.setProperty("org.quartz.scheduler.idleWaitTime", "1500");
		props.setProperty("org.quartz.threadPool.threadCount", "2");
		Scheduler scheduler = new StdSchedulerFactory(props).getScheduler();
		scheduler.getListenerManager().addSchedulerListener(schedulerListener);
		scheduler.getListenerManager().addTriggerListener(triggerListener);
		scheduler.start();
		scheduler.standby();
		
		JobDetail job = newJob(Qtz205Job.class).withIdentity("test").build();
		Trigger trigger = newTrigger().withIdentity("test")
				.withSchedule(simpleSchedule().withIntervalInMilliseconds(250).withRepeatCount(2))
				.build();
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		Thread.sleep(5000);
		
		scheduler.shutdown(true);

		Assert.assertEquals(2, Qtz205Job.jobExecutionCount);
		Assert.assertEquals(3, triggerListener.getFireCount());
		Assert.assertEquals(1, schedulerListener.getTriggerFinalizedCount());
	}
