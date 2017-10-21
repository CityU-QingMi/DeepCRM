  public List<TriggerWrapper> getTriggerWrappersForCalendar(String calName) {
    List<TriggerWrapper> trigList = new ArrayList<TriggerWrapper>();

    for (TriggerKey triggerKey : triggersByFQN.keySet()) {
      TriggerWrapper tw = triggersByFQN.get(triggerKey);
      String tcalName = tw.getCalendarName();
      if (tcalName != null && tcalName.equals(calName)) {
        trigList.add(tw);
      }
    }

    return trigList;
  }
