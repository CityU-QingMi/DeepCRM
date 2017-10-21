	public void testTimeZones() throws Exception {
		Scheduler scheduler = null;
		try {
			// given
			StdSchedulerFactory factory = new StdSchedulerFactory("org/quartz/xml/quartz-test.properties");
			scheduler = factory.getScheduler();
			ClassLoadHelper clhelper = new CascadingClassLoadHelper();
			clhelper.initialize();
			XMLSchedulingDataProcessor processor = new XMLSchedulingDataProcessor(clhelper);

			// when
			processor.processFileAndScheduleJobs("org/quartz/xml/simple-job-trigger-with-timezones.xml", scheduler);

			// then
			Trigger trigger = scheduler.getTrigger(new TriggerKey("job1", "DEFAULT"));
			assertNotNull(trigger);

			assertEquals(dateOfGMT_UTC(18, 0, 0, 1, Calendar.JANUARY, 2012), trigger.getStartTime());
			assertEquals(dateOfGMT_UTC(19, 0, 0, 1, Calendar.JANUARY, 2012), trigger.getEndTime());
			
			
			trigger = scheduler.getTrigger(new TriggerKey("job2", "DEFAULT"));
			assertNotNull(trigger);

			assertEquals(dateOfLocalTime(6, 0, 0, 1, Calendar.JANUARY, 2012), trigger.getStartTime());
			assertEquals(dateOfGMT_UTC(19, 0, 0, 1, Calendar.JANUARY, 2012), trigger.getEndTime());
		} finally {
			if (scheduler != null)
				scheduler.shutdown();
		}
	}
