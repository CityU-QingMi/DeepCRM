  public void testScheduleActualTrigger() throws Exception {
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    JobDetail job = newJob(MyJob.class).build();
    DailyTimeIntervalTrigger trigger = newTrigger().withIdentity("test")
        .withSchedule(dailyTimeIntervalSchedule()
            .withIntervalInSeconds(3))
            .build();
    scheduler.scheduleJob(job, trigger); //We are not verify anything other than just run through the scheduler.
    scheduler.shutdown();
  }
