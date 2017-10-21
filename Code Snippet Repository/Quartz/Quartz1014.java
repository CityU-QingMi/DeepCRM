  @Override
  public void resetTriggerFromErrorState(final TriggerKey triggerKey) throws JobPersistenceException {

    TriggerWrapper tw = triggerFacade.get(triggerKey);
    // was the trigger deleted since being acquired?
    if (tw == null) {
      return;
    }
    // was the trigger completed, paused, blocked, etc. since being acquired?
    if (tw.getState() != TriggerState.ERROR) {
      return;
    }

    if(triggerFacade.pausedGroupsContain(triggerKey.getGroup())) {
      tw.setState(TriggerState.PAUSED, terracottaClientId, triggerFacade);
    }
    else {
      tw.setState(TriggerState.WAITING, terracottaClientId, triggerFacade);
      timeTriggers.add(tw);
    }
  }
