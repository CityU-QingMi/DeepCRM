    public void execute(JobExecutionContext context) throws JobExecutionException {
      System.err.println("Hi there");
      ThreadUtil.reallySleep(5000L);
      System.err.println("Done!");
      try {
        localBarrier.await();
      } catch (Exception e) {
        throw new JobExecutionException(e);
      }
    }
