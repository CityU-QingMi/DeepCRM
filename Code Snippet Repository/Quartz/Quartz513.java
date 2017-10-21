    public void setStartTimeOfDay(TimeOfDay startTimeOfDay) {
        if (startTimeOfDay == null) {
            throw new IllegalArgumentException("Start time of day cannot be null");
        }

        TimeOfDay eTime = getEndTimeOfDay();
        if (eTime != null && eTime.before(startTimeOfDay)) {
            throw new IllegalArgumentException(
                "End time of day cannot be before start time of day");    
        }

        this.startTimeOfDay = startTimeOfDay;
    }
