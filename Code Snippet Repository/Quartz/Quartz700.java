  public void testScheduleInMiddleOfDailyInterval() throws Exception {
    
    java.util.Calendar currTime = java.util.Calendar.getInstance();
    
    int currHour = currTime.get(java.util.Calendar.HOUR);
    
    // this test won't work out well in the early hours, where 'backing up' would give previous day,
    // or where daylight savings transitions could occur and confuse the assertions...
    if(currHour < 3)
      return;
    
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    JobDetail job = newJob(MyJob.class).build();
    Trigger trigger = newTrigger().withIdentity("test")
        .withSchedule(dailyTimeIntervalSchedule()
            .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(2, 15))
            .withIntervalInMinutes(5))
            .startAt(currTime.getTime())
            .build();
    scheduler.scheduleJob(job, trigger); 
    
    trigger = scheduler.getTrigger(trigger.getKey());
    
    System.out.println("testScheduleInMiddleOfDailyInterval: currTime = " + currTime.getTime());
    System.out.println("testScheduleInMiddleOfDailyInterval: computed first fire time = " + trigger.getNextFireTime());
        
    Assert.assertTrue("First fire time is not after now!", trigger.getNextFireTime().after(currTime.getTime()));
    
    
    Date startTime = DateBuilder.todayAt(2, 15, 0);
    
    job = newJob(MyJob.class).build();
    trigger = newTrigger().withIdentity("test2")
        .withSchedule(dailyTimeIntervalSchedule()
            .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(2, 15))
            .withIntervalInMinutes(5))
            .startAt(startTime)
            .build();
    scheduler.scheduleJob(job, trigger); 
    
    trigger = scheduler.getTrigger(trigger.getKey());
    
    System.out.println("testScheduleInMiddleOfDailyInterval: startTime = " + startTime);
    System.out.println("testScheduleInMiddleOfDailyInterval: computed first fire time = " + trigger.getNextFireTime());
        
    Assert.assertTrue("First fire time is not after now!", trigger.getNextFireTime().equals(startTime));
 
    
    scheduler.shutdown();
  }
