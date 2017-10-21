        @Test
	public void testNoConcurrentExecOnSameJobWithBatching() throws Exception {

		List<Date> jobExecDates = Collections.synchronizedList(new ArrayList<Date>());
		CyclicBarrier barrier = new CyclicBarrier(2);
		
		Date startTime = new Date(System.currentTimeMillis() + 100); // make the triggers fire at the same time.
		
		JobDetail job1 = JobBuilder.newJob(TestJob.class).withIdentity("job1").build();
		Trigger trigger1 = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule())
				.startAt(startTime).build();

		Trigger trigger2 = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule())
				.startAt(startTime).forJob(job1.getKey()).build();

		Properties props = new Properties();
		props.setProperty("org.quartz.scheduler.idleWaitTime", "1500");
		props.setProperty("org.quartz.scheduler.batchTriggerAcquisitionMaxCount", "2");
		props.setProperty("org.quartz.threadPool.threadCount", "2");
		Scheduler scheduler = new StdSchedulerFactory(props).getScheduler();
		scheduler.getContext().put(BARRIER, barrier);
		scheduler.getContext().put(DATE_STAMPS, jobExecDates);
		scheduler.getListenerManager().addJobListener(new TestJobListener(2));
		scheduler.scheduleJob(job1, trigger1);
		scheduler.scheduleJob(trigger2);
		scheduler.start();
		
		barrier.await(125, TimeUnit.SECONDS);
		
		scheduler.shutdown(true);
		
                Assert.assertThat(jobExecDates, hasSize(2));
                long fireTimeTrigger1 = jobExecDates.get(0).getTime();
                long fireTimeTrigger2 = jobExecDates.get(1).getTime();
                Assert.assertThat(fireTimeTrigger2 - fireTimeTrigger1, greaterThanOrEqualTo(JOB_BLOCK_TIME));
	}
