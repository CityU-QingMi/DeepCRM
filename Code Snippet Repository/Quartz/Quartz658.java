    @Test
	public void testScheduleMultipleTriggersForAJob() throws SchedulerException {

		
		JobDetail job = newJob(TestJob.class).withIdentity("job1", "group1").build();
		Trigger trigger1 = newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
								.repeatForever())
				.build();
		Trigger trigger2 = newTrigger()
				.withIdentity("trigger2", "group1")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
								.repeatForever())
				.build();
		Set<Trigger> triggersForJob = new HashSet<Trigger>(); 
		triggersForJob.add(trigger1);
		triggersForJob.add(trigger2);
		
		Scheduler sched = createScheduler("testScheduleMultipleTriggersForAJob", 5);
		sched.scheduleJob(job,triggersForJob, true);
		
		List<? extends Trigger> triggersOfJob = sched.getTriggersOfJob(job.getKey());
		assertEquals(2,triggersOfJob.size());
		assertTrue(triggersOfJob.contains(trigger1));
		assertTrue(triggersOfJob.contains(trigger2));
		
		sched.shutdown(true);
	}
