  private static void test(Scheduler scheduler) throws Throwable {
    JobDetailImpl jobDetail = new JobDetailImpl("testjob", null, SimpleJob.class);
    jobDetail.getJobDataMap().put("await-time", 150);
    jobDetail.setDurability(true);


    // This calendar doesn't do anything really, just testing that calendars work
    if (!scheduler.getCalendarNames().contains("mycal")) {
      scheduler.addCalendar("mycal", new BaseCalendar(), false, true);
    }
    if (!scheduler.checkExists(jobDetail.getKey())) {
      scheduler.addJob(jobDetail, false);
    }
    
    Trigger trigger = TriggerBuilder.newTrigger().forJob("testjob").modifiedByCalendar("mycal").build();
    scheduler.scheduleJob(trigger);
    SimpleJob.localBarrier.await();
  }
