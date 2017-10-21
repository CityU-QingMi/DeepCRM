  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    Scheduler scheduler = null;

    try {
      StdSchedulerFactory factory = new StdSchedulerFactory("basic-quartz.properties");
      scheduler = factory.getScheduler();
      scheduler.start();

      JobDetailImpl jobDetail = new JobDetailImpl("testjob", null, BasicContainerTestJob.class);
      jobDetail.setDurability(true);

      SimpleTriggerImpl trigger = new SimpleTriggerImpl("trigger1", "group");
      trigger.setRepeatInterval(Long.MAX_VALUE);
      trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
      trigger.setJobName("testjob");

      scheduler.addJob(jobDetail, false);
      scheduler.scheduleJob(trigger);

      BasicContainerTestJob.localBarrier.await();

      findMBean();

      resp.getWriter().println("OK");
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (scheduler != null) {
        try {
          scheduler.shutdown();
        } catch (SchedulerException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
