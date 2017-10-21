  @Override
  public List<OperableTrigger> getTriggersForJob(final JobKey jobKey) throws JobPersistenceException {
    List<OperableTrigger> trigList = new ArrayList<OperableTrigger>();

    lock();
    try {
      for (TriggerKey triggerKey : triggerFacade.allTriggerKeys()) {
        TriggerWrapper tw = triggerFacade.get(triggerKey);
        if (tw.getJobKey().equals(jobKey)) {
          trigList.add(tw.getTriggerClone());
        }
      }
    } finally {
      unlock();
    }

    return trigList;
  }
