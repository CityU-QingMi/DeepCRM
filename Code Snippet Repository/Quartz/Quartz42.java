    public DailyTimeIntervalScheduleBuilder endingDailyAfterCount(int count) {
        if(count <=0)
            throw new IllegalArgumentException("Ending daily after count must be a positive number!");
        
        if(startTimeOfDay == null)
            throw new IllegalArgumentException("You must set the startDailyAt() before calling this endingDailyAfterCount()!");
        
        Date today = new Date();
        Date startTimeOfDayDate = startTimeOfDay.getTimeOfDayForDate(today);
        Date maxEndTimeOfDayDate = TimeOfDay.hourMinuteAndSecondOfDay(23, 59, 59).getTimeOfDayForDate(today);
        long remainingMillisInDay = maxEndTimeOfDayDate.getTime() - startTimeOfDayDate.getTime();
        long intervalInMillis;
        if (intervalUnit == IntervalUnit.SECOND)
            intervalInMillis = interval * 1000L;
        else if (intervalUnit == IntervalUnit.MINUTE)
                intervalInMillis = interval * 1000L * 60;
        else if (intervalUnit == IntervalUnit.HOUR)
            intervalInMillis = interval * 1000L * 60 * 24;
        else
            throw new IllegalArgumentException("The IntervalUnit: " + intervalUnit + " is invalid for this trigger."); 
        
        if (remainingMillisInDay - intervalInMillis <= 0)
            throw new IllegalArgumentException("The startTimeOfDay is too late with given Interval and IntervalUnit values.");
        
        long maxNumOfCount = (remainingMillisInDay / intervalInMillis);
        if (count > maxNumOfCount)
            throw new IllegalArgumentException("The given count " + count + " is too large! The max you can set is " + maxNumOfCount);
        
        long incrementInMillis = (count - 1) * intervalInMillis;
        Date endTimeOfDayDate = new Date(startTimeOfDayDate.getTime() + incrementInMillis);
        
        if (endTimeOfDayDate.getTime() > maxEndTimeOfDayDate.getTime())
            throw new IllegalArgumentException("The given count " + count + " is too large! The max you can set is " + maxNumOfCount);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(endTimeOfDayDate);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        
        endTimeOfDay = TimeOfDay.hourMinuteAndSecondOfDay(hour, minute, second);
        return this;
    }
