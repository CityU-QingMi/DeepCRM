  @Override
  public void pauseAll() throws JobPersistenceException {

    lock();
    try {
      List<String> names = getTriggerGroupNames();

      for (String name : names) {
        pauseTriggers(GroupMatcher.triggerGroupEquals(name));
      }
    } finally {
      unlock();
    }
  }
