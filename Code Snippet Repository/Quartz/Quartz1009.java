  private boolean removeTrigger(TriggerKey triggerKey, boolean removeOrphanedJob) throws JobPersistenceException {

    lock();
    TriggerWrapper tw = null;
    try {
      // remove from triggers by FQN map
      tw = triggerFacade.remove(triggerKey);

      if (tw != null) {
        // remove from triggers by group
        Set<String> grpSet = toolkitDSHolder.getOrCreateTriggersGroupMap(triggerKey.getGroup());
        grpSet.remove(triggerKey.getName());
        if (grpSet.size() == 0) {
          toolkitDSHolder.removeTriggersGroupMap(triggerKey.getGroup());
          triggerFacade.removeGroup(triggerKey.getGroup());
        }
        // remove from triggers array
        timeTriggers.remove(tw);

        if (removeOrphanedJob) {
          JobWrapper jw = jobFacade.get(tw.getJobKey());
          List<OperableTrigger> trigs = getTriggersForJob(tw.getJobKey());
          if ((trigs == null || trigs.size() == 0) && !jw.isDurable()) {
            JobKey jobKey = tw.getJobKey();
            if (removeJob(jobKey)) {
              signaler.notifySchedulerListenersJobDeleted(jobKey);
            }
          }
        }
      }
    } finally {
      unlock();
    }

    return tw != null;
  }
