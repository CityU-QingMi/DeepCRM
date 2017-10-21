    private Date advanceToNextDayOfWeekIfNecessary(Date fireTime, boolean forceToAdvanceNextDay) {
        // a. Advance or adjust to next dayOfWeek if need to first, starting next day with startTimeOfDay.
        TimeOfDay sTimeOfDay = getStartTimeOfDay();
        Date fireTimeStartDate = sTimeOfDay.getTimeOfDayForDate(fireTime);      
        Calendar fireTimeStartDateCal = createCalendarTime(fireTimeStartDate);          
        int dayOfWeekOfFireTime = fireTimeStartDateCal.get(Calendar.DAY_OF_WEEK);
        
        // b2. We need to advance to another day if isAfterTimePassEndTimeOfDay is true, or dayOfWeek is not set.
        Set<Integer> daysOfWeekToFire = getDaysOfWeek();
        if (forceToAdvanceNextDay || !daysOfWeekToFire.contains(dayOfWeekOfFireTime)) {
          // Advance one day at a time until next available date.
          for(int i=1; i <= 7; i++) {
            fireTimeStartDateCal.add(Calendar.DATE, 1);
            dayOfWeekOfFireTime = fireTimeStartDateCal.get(Calendar.DAY_OF_WEEK);
            if (daysOfWeekToFire.contains(dayOfWeekOfFireTime)) {
              fireTime = fireTimeStartDateCal.getTime();
              break;
            }
          }
        }
        
        // Check fireTime not pass the endTime
         Date eTime = getEndTime();
         if (eTime != null && fireTime.getTime() > eTime.getTime()) {
             return null;
         }

        return fireTime;
    }
