    @SuppressWarnings("")
    public void testPauseJobGroupPausesNewJob() throws Exception
    {
    	// Pausing job groups in JDBCJobStore is broken, see QTZ-208
    	if (fJobStore instanceof JobStoreSupport)
    		return;
    	
    	final String jobName1 = "PauseJobGroupPausesNewJob";
    	final String jobName2 = "PauseJobGroupPausesNewJob2";
    	final String jobGroup = "PauseJobGroupPausesNewJobGroup";
    
    	JobDetailImpl detail = new JobDetailImpl(jobName1, jobGroup, MyJob.class);
    	detail.setDurability(true);
    	fJobStore.storeJob(detail, false);
    	fJobStore.pauseJobs(GroupMatcher.jobGroupEquals(jobGroup));
    
    	detail = new JobDetailImpl(jobName2, jobGroup, MyJob.class);
    	detail.setDurability(true);
    	fJobStore.storeJob(detail, false);
    
    	String trName = "PauseJobGroupPausesNewJobTrigger";
    	String trGroup = "PauseJobGroupPausesNewJobTriggerGroup";
    	OperableTrigger tr = new SimpleTriggerImpl(trName, trGroup, new Date());
        tr.setJobKey(new JobKey(jobName2, jobGroup));
    	fJobStore.storeTrigger(tr, false);
    	assertEquals(TriggerState.PAUSED, fJobStore.getTriggerState(tr.getKey()));
    }
