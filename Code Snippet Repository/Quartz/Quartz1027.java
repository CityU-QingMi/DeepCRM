  @Override
  public void resumeTrigger(TriggerKey triggerKey) throws JobPersistenceException {
    lock();
    try {
      TriggerWrapper tw = triggerFacade.get(triggerKey);

      // does the trigger exist?
      if (tw == null) { return; }

      // if the trigger is not paused resuming it does not make sense...
      if (tw.getState() != TriggerState.PAUSED && tw.getState() != TriggerState.PAUSED_BLOCKED) { return; }

      if (jobFacade.blockedJobsContain(tw.getJobKey())) {
        tw.setState(TriggerState.BLOCKED, terracottaClientId, triggerFacade);
      } else {
        tw.setState(TriggerState.WAITING, terracottaClientId, triggerFacade);
      }

      applyMisfire(tw);

      if (tw.getState() == TriggerState.WAITING) {
        timeTriggers.add(tw);
      }
    } finally {
      unlock();
    }
  }
