  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.err.println("Hi There");

    if (context.getMergedJobDataMap().getString(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_NAME) == null) {
      // If not recovering, just exit the VM
      try {
        Client1.localBarrier.await();
      } catch (Exception e) {
        throw new JobExecutionException(e);
      }
      try {
        Thread.currentThread().join();
      } catch (InterruptedException ex) {
        throw new JobExecutionException(ex);
      }
    } else {
      assertThat(context.getMergedJobDataMap().getBooleanValue(RecoveryTest.class.getName()), is(true));
      try {
        Client2.localBarrier.await();
      } catch (Exception e) {
        throw new JobExecutionException(e);
      }
    }
  }
