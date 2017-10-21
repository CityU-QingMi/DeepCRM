    @Override
    protected void test(Scheduler scheduler) throws Exception {
      JobDetail job0 = JobBuilder.newJob(NullJob.class).withIdentity("job0").build();
      Trigger trigger0 = TriggerBuilder.newTrigger().withIdentity("trigger0").startAt(new Date(Long.MAX_VALUE)).build();
      scheduler.scheduleJob(job0, trigger0);
      
      initiateRejoin();

      Set<Integer> successes = new HashSet<Integer>();
      for (int i = 1; i < 100; i++) {
        JobDetail job = JobBuilder.newJob(NullJob.class).withIdentity("job" + i).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + i).startAt(new Date(Long.MAX_VALUE)).build();
        try {
          scheduler.scheduleJob(job, trigger);
          successes.add(i);
        } catch (JobPersistenceException e) {
          //JobPersistenceException == In Doubt
          Thread.sleep(1000);
        }
      }

      for (int i = 0; i < 100; i++) {
        if (successes.contains(i)) {
          Assert.assertTrue("Job " + i, scheduler.checkExists(new JobKey("job" + i)));
          Assert.assertTrue("Trigger " + i, scheduler.checkExists(new TriggerKey("trigger" + i)));
        }
      }
    }
