   	public void testQTZ327SimpleTriggerNoRepeat() throws Exception {
   		Scheduler scheduler = null;
   		try {
   			StdSchedulerFactory factory = new StdSchedulerFactory("org/quartz/xml/quartz-test.properties");
   			scheduler = factory.getScheduler();
   			ClassLoadHelper clhelper = new CascadingClassLoadHelper();
   			clhelper.initialize();
   			XMLSchedulingDataProcessor processor = new XMLSchedulingDataProcessor(clhelper);
   			processor.processFileAndScheduleJobs("org/quartz/xml/simple-job-trigger-no-repeat.xml", scheduler);
   			assertEquals(1, scheduler.getJobKeys(GroupMatcher.jobGroupEquals("DEFAULT")).size());
   			assertEquals(1, scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals("DEFAULT")).size());
   		} finally {
   			if (scheduler != null)
   				scheduler.shutdown();
   		}
   	}
