  @Override
  public boolean removeCalendar(String calName) throws JobPersistenceException {
    int numRefs = 0;

    lock();
    try {
      for (TriggerKey triggerKey : triggerFacade.allTriggerKeys()) {
        TriggerWrapper tw = triggerFacade.get(triggerKey);
        if (tw.getCalendarName() != null && tw.getCalendarName().equals(calName)) {
          numRefs++;
        }
      }

      if (numRefs > 0) { throw new JobPersistenceException("Calender cannot be removed if it referenced by a Trigger!"); }

      return (calendarsByName.remove(calName) != null);
    } finally {
      unlock();
    }
  }
