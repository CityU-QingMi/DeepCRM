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
      Trigger trigger1 = newTrigger().withIdentity("trigger").build();
      scheduler.scheduleJob(jobDetail, trigger1);
    
      scheduler.start();
      localBarrier.await();
      while (scheduler.checkExists(trigger1.getKey())) {
        Thread.sleep(50);
      }

      Assert.assertFalse(scheduler.checkExists(jobDetail.getKey()));
      scheduler.shutdown(true);
    }
