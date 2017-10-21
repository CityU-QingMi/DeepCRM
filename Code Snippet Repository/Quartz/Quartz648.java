	public void testStoreAndRetriveTriggers() throws Exception {
        SchedulerSignaler schedSignaler = new SampleSignaler();
        ClassLoadHelper loadHelper = new CascadingClassLoadHelper();
        loadHelper.initialize();

        JobStore store = createJobStore("testStoreAndRetriveTriggers");
        store.initialize(loadHelper, schedSignaler);
		
		// Store jobs and triggers.
		for (int i=0; i < 10; i++) {
            String group =  i < 5 ? "a" : "b";
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job" + i, group).build();
			store.storeJob(job, true);
			SimpleScheduleBuilder schedule = SimpleScheduleBuilder.simpleSchedule();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("job" + i, group).withSchedule(schedule).forJob(job).build();
			store.storeTrigger((OperableTrigger)trigger, true);
		}
		// Retrieve job and trigger.
		for (int i=0; i < 10; i++) {
            String group =  i < 5 ? "a" : "b";
			JobKey jobKey = JobKey.jobKey("job" + i, group);
			JobDetail storedJob = store.retrieveJob(jobKey);
			Assert.assertEquals(jobKey, storedJob.getKey());
			
			TriggerKey triggerKey = TriggerKey.triggerKey("job" + i, group);
			Trigger storedTrigger = store.retrieveTrigger(triggerKey);
			Assert.assertEquals(triggerKey, storedTrigger.getKey());
		}
        // Retrieve by group
        Assert.assertEquals("Wrong number of triggers in group 'a'", store.getTriggerKeys(GroupMatcher.triggerGroupEquals("a")).size(), 5);
        Assert.assertEquals("Wrong number of triggers in group 'b'", store.getTriggerKeys(GroupMatcher.triggerGroupEquals("b")).size(), 5);
	}
