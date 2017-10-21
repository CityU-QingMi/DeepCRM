    @Test
    public void replaceJobDetail() throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("testjob3").storeDurably().build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testjob3")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        modifyStoredJobClassName();

        jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("testjob3").storeDurably().build();
        scheduler.addJob(jobDetail, true);
    }
