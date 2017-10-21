  @Override
  public void resumeAll() throws JobPersistenceException {

    lock();
    try {
      jobFacade.clearPausedJobGroups();
      List<String> names = getTriggerGroupNames();

      for (String name : names) {
        resumeTriggers(GroupMatcher.triggerGroupEquals(name));
      }
    } finally {
      unlock();
    }
  }
