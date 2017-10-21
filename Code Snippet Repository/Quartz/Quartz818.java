    @Test
    public void testJobDataMapDirtyFlag() throws Exception {
        JobDetail jobDetail = newJob(HelloJob.class)
                .withIdentity("test")
                .usingJobData("jfoo", "bar")
                .build();

        CronTrigger trigger = newTrigger()
                .withIdentity("test")
                .withSchedule(cronSchedule("0 0 0 * * ?"))
                .usingJobData("tfoo", "bar")
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        JobDetail storedJobDetail = scheduler.getJobDetail(JobKey.jobKey("test"));
        JobDataMap storedJobMap = storedJobDetail.getJobDataMap();
        Assert.assertThat(storedJobMap.isDirty(), is(false));

        Trigger storedTrigger = scheduler.getTrigger(triggerKey("test"));
        JobDataMap storedTriggerMap = storedTrigger.getJobDataMap();
        Assert.assertThat(storedTriggerMap.isDirty(), is(false));
    }
