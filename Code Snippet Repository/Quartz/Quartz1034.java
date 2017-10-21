  boolean applyMisfire(TriggerWrapper tw) throws JobPersistenceException {
    long misfireTime = System.currentTimeMillis();
    if (getMisfireThreshold() > 0) {
      misfireTime -= getMisfireThreshold();
    }

    Date tnft = tw.getNextFireTime();
    if (tnft == null || tnft.getTime() > misfireTime
        || tw.getMisfireInstruction() == Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY) { return false; }

    Calendar cal = null;
    if (tw.getCalendarName() != null) {
      cal = retrieveCalendar(tw.getCalendarName());
    }

    signaler.notifyTriggerListenersMisfired(tw.getTriggerClone());

    tw.updateAfterMisfire(cal, triggerFacade);

    if (tw.getNextFireTime() == null) {
      tw.setState(TriggerState.COMPLETE, terracottaClientId, triggerFacade);
      signaler.notifySchedulerListenersFinalized(tw.getTriggerClone());
      timeTriggers.remove(tw);
    } else if (tnft.equals(tw.getNextFireTime())) { return false; }

    return true;
  }
