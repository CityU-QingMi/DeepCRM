    public void execute(JobExecutionContext context) throws JobExecutionException {
      try {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        int outputCount = data.getIntValue(JOB_PROP_NAME) + 1;
        data.put(JOB_PROP_NAME, outputCount);
        jobBarrier.await();
        jobBarrier.await();
      } catch (Exception e) {
        throw new JobExecutionException(e);
      }
    }
