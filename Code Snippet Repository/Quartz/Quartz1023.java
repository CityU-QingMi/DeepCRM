  @Override
  public void pauseTrigger(TriggerKey triggerKey) throws JobPersistenceException {
    lock();
    try {
      TriggerWrapper tw = triggerFacade.get(triggerKey);

      // does the trigger exist?
      if (tw == null) { return; }

      // if the trigger is "complete" pausing it does not make sense...
      if (tw.getState() == TriggerState.COMPLETE) { return; }

      if (tw.getState() == TriggerState.BLOCKED) {
        tw.setState(TriggerState.PAUSED_BLOCKED, terracottaClientId, triggerFacade);
      } else {
        tw.setState(TriggerState.PAUSED, terracottaClientId, triggerFacade);
      }

      timeTriggers.remove(tw);
      if (triggerRemovedFromCandidateFiringListHandler != null) {
        triggerRemovedFromCandidateFiringListHandler.removeCandidateTrigger(tw);
      }
    } finally {
      unlock();
    }
  }
