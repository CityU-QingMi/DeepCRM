    @Override
    protected void test(Scheduler scheduler) throws Throwable {
      final CyclicBarrier localBarrier = new CyclicBarrier(2);
      
      scheduler.setJobFactory(new JobFactory() {

        @Override
        public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
          JobDetail jobDetail = bundle.getJobDetail();
          Class<? extends Job> jobClass = jobDetail.getJobClass();
          try {
            return jobClass.getConstructor(CyclicBarrier.class).newInstance(localBarrier);
          } catch (Exception e) {
            throw new SchedulerException(e);
          }
        }
      });

      
      JobDetail jobDetail = newJob(TestJob.class).withIdentity("job").build();
      jobDetail.getJobDataMap().put("id", "job");
      jobDetail.getJobDataMap().put(TestJob.JOB_PROP_NAME, 0);
      jobDetail.requestsRecovery();

      Trigger trigger = newTrigger().withIdentity("trigger")
              .withSchedule(simpleSchedule()
              .withIntervalInSeconds(2)
              .withRepeatCount(1))
              .build();
      scheduler.scheduleJob(jobDetail, trigger);
    
      // start node1's scheduler first to let it run some jobs, then switch to node2
      JobDetail lastRunningJob = null;
      List<JobExecutionContext> contextList = null;

      scheduler.start();
      localBarrier.await();
      contextList = scheduler.getCurrentlyExecutingJobs();
      Assert.assertEquals(contextList.size(), 1);
      lastRunningJob = contextList.get(0).getJobDetail();
      localBarrier.await();
      scheduler.shutdown(true);
    
      SchedulerMetaData metaData = scheduler.getMetaData();
      int numJobsExecuted = metaData.getNumberOfJobsExecuted();
      int finalCount = lastRunningJob.getJobDataMap().getIntValue(TestJob.JOB_PROP_NAME);

      Assert.assertThat(numJobsExecuted, Is.is(1));
      Assert.assertThat(finalCount, Is.is(1));
    }
