  @Override
  public Calendar retrieveCalendar(String calName) throws JobPersistenceException {
    lock();
    try {
      Calendar cw = calendarsByName.get(calName);
      return (Calendar) (cw == null ? null : cw.clone());
    } finally {
      unlock();
    }
  }
