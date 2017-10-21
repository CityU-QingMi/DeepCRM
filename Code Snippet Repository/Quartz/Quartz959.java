    @Override
    protected void test(Scheduler scheduler) throws Throwable {
      JobDetail job = JobBuilder.newJob().ofType(NullJob.class)
              .withIdentity("ConstantJobFlipFlopTest", "job").build();
      Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
              .withIdentity("ConstantJobFlipFlopTest", "trigger").build();
      
      Assert.assertThat(scheduler.scheduleJob(job, trigger), IsNull.notNullValue());
      while (scheduler.checkExists(job.getKey())) {
        Thread.sleep(50);
      }
      Assert.assertThat(scheduler.scheduleJob(job, trigger), IsNull.notNullValue());
    }
