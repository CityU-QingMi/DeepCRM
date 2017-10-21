  public void execute(JobExecutionContext context) throws JobExecutionException {

    // This job simply prints out its job name and the
    // date and time that it is running
    JobKey jobKey = context.getJobDetail().getKey();
    _log.info("Executing job: " + jobKey + " executing at " + new Date());

    // wait for a period of time
    long delayTime = context.getJobDetail().getJobDataMap().getLong(DELAY_TIME);
    try {
      Thread.sleep(delayTime);
    } catch (Exception e) {
      //
    }

    _log.info("Finished Executing job: " + jobKey + " at " + new Date());
  }
