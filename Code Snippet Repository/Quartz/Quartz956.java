    @Override
    public boolean isConcurrentExectionDisallowed() {
      try {
        Assert.assertTrue(SCHEDULER.get().checkExists(new JobKey("job-name", "job-group")));
      } catch (SchedulerException e) {
        throw new AssertionError(e);
      }
      Runtime.getRuntime().halt(0);
      throw new AssertionError();
    }
