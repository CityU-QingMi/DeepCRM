  public void execute(JobExecutionContext context) throws JobExecutionException {
    Integer awaitTime = (Integer)context.getJobDetail().getJobDataMap().get("await-time");
    if (awaitTime == null) {
      awaitTime = 10;
    }

    System.err.println("Hi there");
    try {
      localBarrier.await(awaitTime, TimeUnit.SECONDS);
    } catch (Exception e) {
      throw new JobExecutionException(e);
    }
  }
