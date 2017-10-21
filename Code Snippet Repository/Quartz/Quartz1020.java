  @Override
  public List<String> getCalendarNames() throws JobPersistenceException {
    lock();
    try {
      Set<String> names = calendarsByName.keySet();
      return new ArrayList<String>(names);
    } finally {
      unlock();
    }
  }
