    public void execute(JobExecutionContext context) throws JobExecutionException {
      try {
        localBarrier.await();
      } catch (Exception e) {
        throw new JobExecutionException(e);
      }

      try {
        localBarrier.await();
      } catch (Exception e) {
        throw new JobExecutionException(e);
      }
    }
