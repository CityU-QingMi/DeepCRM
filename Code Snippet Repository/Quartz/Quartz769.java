    @Test
    public void deleteTrigger() throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("testjob3").storeDurably().build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testjob3")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        modifyStoredJobClassName();

        scheduler.unscheduleJob(trigger.getKey());
    }
