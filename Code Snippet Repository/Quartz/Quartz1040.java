  private void setAllTriggersOfJobToState(JobKey jobKey, TriggerState state) {
    List<TriggerWrapper> tws = triggerFacade.getTriggerWrappersForJob(jobKey);

    for (TriggerWrapper tw : tws) {
      tw.setState(state, terracottaClientId, triggerFacade);
      if (state != TriggerState.WAITING) {
        timeTriggers.remove(tw);
      }
    }
  }
