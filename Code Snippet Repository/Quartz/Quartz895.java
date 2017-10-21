    @Override
    public void jobWasExecuted(JobExecutionContext context,
            JobExecutionException jobException) {
        this.jobException = jobException;
        try {
            barrier.await(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
