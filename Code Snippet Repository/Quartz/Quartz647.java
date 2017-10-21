    public void testStoreAndRetrieveJobs() throws Exception {
        SchedulerSignaler schedSignaler = new SampleSignaler();
        ClassLoadHelper loadHelper = new CascadingClassLoadHelper();
        loadHelper.initialize();

        JobStore store = createJobStore("testStoreAndRetrieveJobs");
        store.initialize(loadHelper, schedSignaler);
		
		// Store jobs.
		for (int i=0; i < 10; i++) {
            String group =  i < 5 ? "a" : "b";
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job" + i, group).build();
			store.storeJob(job, false);
		}
		// Retrieve jobs.
		for (int i=0; i < 10; i++) {
            String group =  i < 5 ? "a" : "b";
			JobKey jobKey = JobKey.jobKey("job" + i, group);
			JobDetail storedJob = store.retrieveJob(jobKey);
			Assert.assertEquals(jobKey, storedJob.getKey());
		}
        // Retrieve by group
        Assert.assertEquals("Wrong number of jobs in group 'a'", store.getJobKeys(GroupMatcher.jobGroupEquals("a")).size(), 5);
        Assert.assertEquals("Wrong number of jobs in group 'b'", store.getJobKeys(GroupMatcher.jobGroupEquals("b")).size(), 5);
	}
