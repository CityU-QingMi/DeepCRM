  @Override
  public boolean removeJobs(List<JobKey> jobKeys) throws JobPersistenceException {
    boolean allFound = true;

    lock();
    try {
      for (JobKey key : jobKeys)
        allFound = removeJob(key) && allFound;
    } finally {
      unlock();
    }

    return allFound;
  }
