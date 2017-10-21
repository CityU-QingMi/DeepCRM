    @Override
    public int compare(CalendarIntervalTriggerImpl o1, CalendarIntervalTriggerImpl o2) {
      return o1.getJobKey().equals(o2.getJobKey())
              && o1.getKey().equals(o2.getKey())
              && nullSafeEquals(o1.getDescription(), o2.getDescription())
              && (o1.getJobDataMap() == null) == (o2.getJobDataMap() == null)
              && o1.getCalendarName().equals(o2.getCalendarName())
              && o1.getStartTime().equals(o2.getStartTime())
              && o1.getEndTime().equals(o2.getEndTime())
              && nullSafeEquals(o1.getNextFireTime(), o2.getNextFireTime())
              && nullSafeEquals(o1.getPreviousFireTime(), o2.getPreviousFireTime())
              && o1.getRepeatInterval() == o2.getRepeatInterval()
              && o1.getRepeatIntervalUnit().equals(o2.getRepeatIntervalUnit())
              && o1.getTimeZone().equals(o2.getTimeZone())
              && o1.isPreserveHourOfDayAcrossDaylightSavings() == o2.isPreserveHourOfDayAcrossDaylightSavings()
              && o1.isSkipDayIfHourDoesNotExist() == o2.isSkipDayIfHourDoesNotExist()
              && o1.getTimesTriggered() == o2.getTimesTriggered()
              && nullSafeEquals(o1.getFireInstanceId(), o2.getFireInstanceId())
              && o1.getMisfireInstruction() == o2.getMisfireInstruction()
              && o1.getPriority() == o2.getPriority() ? 0 : -1;
    }
