    @Test
    public void testTriggerFiring() throws Exception {
        final int jobCount = 100;
        final int execCount = 5;

        Scheduler scheduler = createScheduler("testTriggerFiring", 2);
        try {
            for (int i = 0; i < jobCount; i++) {
                String jobName = "myJob" + i;
                JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity(jobName, "myJobGroup")
                        .usingJobData("data", 0).storeDurably().requestRecovery().build();

                Trigger trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity("triggerName" + i, "triggerGroup")
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
                        .withRepeatCount(execCount - 1)).build();

                if (!scheduler.checkExists(jobDetail.getKey())) {
                    scheduler.scheduleJob(jobDetail, trigger);
                }
            }

            scheduler.start();

            for (int i = 0; i < TimeUnit.MINUTES.toSeconds(5); i++) {
                int doneCount = 0;
                for (int j = 0; j < jobCount; j++) {
                    JobDetail jobDetail = scheduler.getJobDetail(new JobKey("myJob" + i, "myJobGroup"));
                    if (jobDetail.getJobDataMap().getInt("data") >= execCount) {
                        doneCount++;
                    }
                }
                if (doneCount == jobCount) {
                    return;
                }
                TimeUnit.SECONDS.sleep(1);
            }
            Assert.fail();
        } finally {
            scheduler.shutdown(true);
        }
    }
