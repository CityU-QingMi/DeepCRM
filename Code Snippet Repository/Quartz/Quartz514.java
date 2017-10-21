    public void setEndTimeOfDay(TimeOfDay endTimeOfDay) {
        if (endTimeOfDay == null) 
            throw new IllegalArgumentException("End time of day cannot be null");

        TimeOfDay sTime = getStartTimeOfDay();
        if (sTime != null && endTimeOfDay.before(endTimeOfDay)) {
            throw new IllegalArgumentException(
                    "End time of day cannot be before start time of day");
        }
        this.endTimeOfDay = endTimeOfDay;
    }
