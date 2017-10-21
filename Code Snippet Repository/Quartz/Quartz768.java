    @Test
    public void deleteJobDetailWithTrigger() throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("testjob2").storeDurably().build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testjob2")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        modifyStoredJobClassName();

        scheduler.deleteJob(jobDetail.getKey());
    }
