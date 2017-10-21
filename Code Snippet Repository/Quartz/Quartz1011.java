  @Override
  public OperableTrigger retrieveTrigger(TriggerKey triggerKey) throws JobPersistenceException {
    lock();
    try {
      TriggerWrapper tw = triggerFacade.get(triggerKey);
      return (tw != null) ? (OperableTrigger) tw.getTriggerClone() : null;
    } finally {
      unlock();
    }
  }
