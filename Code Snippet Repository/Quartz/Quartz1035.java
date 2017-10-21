  @Override
  public List<OperableTrigger> acquireNextTriggers(long noLaterThan, int maxCount, long timeWindow)
      throws JobPersistenceException {
    List<OperableTrigger> result = new ArrayList<OperableTrigger>();;
    lock();
    try {
      for (TriggerWrapper tw : getNextTriggerWrappers(timeTriggers, noLaterThan, maxCount, timeWindow)) {
        result.add(markAndCloneTrigger(tw));
      }
      return result;
    } finally {
      try {
        unlock();
      } catch (RejoinException e) {
        if (!validateAcquired(result)) {
          throw e;
        }
      }
    }
  }
