  @Override
  public Trigger.TriggerState getTriggerState(org.quartz.TriggerKey key) throws JobPersistenceException {

    TriggerWrapper tw;
    lock();
    try {
      tw = triggerFacade.get(key);
    } finally {
      unlock();
    }

    if (tw == null) { return Trigger.TriggerState.NONE; }

    if (tw.getState() == TriggerState.COMPLETE) { return Trigger.TriggerState.COMPLETE; }

    if (tw.getState() == TriggerState.PAUSED) { return Trigger.TriggerState.PAUSED; }

    if (tw.getState() == TriggerState.PAUSED_BLOCKED) { return Trigger.TriggerState.PAUSED; }

    if (tw.getState() == TriggerState.BLOCKED) { return Trigger.TriggerState.BLOCKED; }

    if (tw.getState() == TriggerState.ERROR) { return Trigger.TriggerState.ERROR; }

    return Trigger.TriggerState.NORMAL;
  }
