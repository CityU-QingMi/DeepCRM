	@Test
	public void testQuartz() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.getListenerManager().addJobListener(this);
		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1").build();
		org.quartz.Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1").startNow().build();
		sched.scheduleJob(job, trigger);
		sched.start();
		await();
		sched.shutdown(true);
	}
