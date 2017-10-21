    @Override
    public void validate() throws SchedulerException {
        super.validate();
        
        if (repeatIntervalUnit == null || !(repeatIntervalUnit.equals(IntervalUnit.SECOND) || 
                repeatIntervalUnit.equals(IntervalUnit.MINUTE) ||repeatIntervalUnit.equals(IntervalUnit.HOUR)))
            throw new SchedulerException("Invalid repeat IntervalUnit (must be SECOND, MINUTE or HOUR).");
        if (repeatInterval < 1) {
            throw new SchedulerException("Repeat Interval cannot be zero.");
        }
        
        // Ensure interval does not exceed 24 hours
        long secondsInHour = 24 * 60 * 60L;
        if (repeatIntervalUnit == IntervalUnit.SECOND && repeatInterval > secondsInHour) {
            throw new SchedulerException("repeatInterval can not exceed 24 hours (" + secondsInHour + " seconds). Given " + repeatInterval);
        }
        if (repeatIntervalUnit == IntervalUnit.MINUTE && repeatInterval > secondsInHour / 60L) {
            throw new SchedulerException("repeatInterval can not exceed 24 hours (" + secondsInHour / 60L + " minutes). Given " + repeatInterval);
        }
        if (repeatIntervalUnit == IntervalUnit.HOUR && repeatInterval > 24 ) {
            throw new SchedulerException("repeatInterval can not exceed 24 hours. Given " + repeatInterval + " hours.");
        }        
        
        // Ensure timeOfDay is in order.
        if (getEndTimeOfDay() != null && !getStartTimeOfDay().before(getEndTimeOfDay())) {
            throw new SchedulerException("StartTimeOfDay " + startTimeOfDay + " should not come after endTimeOfDay " + endTimeOfDay);
        }
    }
