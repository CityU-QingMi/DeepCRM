@Override
  public void storeCalendar(String name, Calendar calendar, boolean replaceExisting, boolean updateTriggers)
      throws ObjectAlreadyExistsException, JobPersistenceException {

    Calendar clone = (Calendar) calendar.clone();

    lock();
    try {
      Calendar cal = calendarsByName.get(name);

      if (cal != null && replaceExisting == false) {
        throw new ObjectAlreadyExistsException("Calendar with name '" + name + "' already exists.");
      } else if (cal != null) {
        calendarsByName.remove(name);
      }

      Calendar cw = clone;
      calendarsByName.putNoReturn(name, cw);

      if (cal != null && updateTriggers) {
        for (TriggerWrapper tw : triggerFacade.getTriggerWrappersForCalendar(name)) {
          boolean removed = timeTriggers.remove(tw);

          tw.updateWithNewCalendar(clone, getMisfireThreshold(), triggerFacade);

          if (removed) {
            timeTriggers.add(tw);
          }
        }
      }
    } finally {
      unlock();
    }
  }
