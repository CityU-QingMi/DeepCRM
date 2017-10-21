  public List<TriggerWrapper> getTriggerWrappersForJob(JobKey key) {
    List<TriggerWrapper> trigList = new ArrayList<TriggerWrapper>();

    for (TriggerKey triggerKey : triggersByFQN.keySet()) {
      TriggerWrapper tw = triggersByFQN.get(triggerKey);
      if (tw.getJobKey().equals(key)) {
        trigList.add(tw);
      }
    }

    return trigList;
  }
