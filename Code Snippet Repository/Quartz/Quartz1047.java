  @Override
  public boolean removeTriggers(List<TriggerKey> triggerKeys) throws JobPersistenceException {
    boolean allFound = true;

    lock();
    try {
      for (TriggerKey key : triggerKeys)
        allFound = removeTrigger(key) && allFound;
    } finally {
      unlock();
    }

    return allFound;
  }
