    @Override
    public boolean isTimeIncluded(long timeInMillis) {        
        if ((getBaseCalendar() != null) && 
                (getBaseCalendar().isTimeIncluded(timeInMillis) == false)) {
            return false;
        }
        
        long startOfDayInMillis = getStartOfDayJavaCalendar(timeInMillis).getTime().getTime();
        long endOfDayInMillis = getEndOfDayJavaCalendar(timeInMillis).getTime().getTime();
        long timeRangeStartingTimeInMillis = 
            getTimeRangeStartingTimeInMillis(timeInMillis);
        long timeRangeEndingTimeInMillis = 
            getTimeRangeEndingTimeInMillis(timeInMillis);
        if (!invertTimeRange) {
            return 
                ((timeInMillis > startOfDayInMillis && 
                    timeInMillis < timeRangeStartingTimeInMillis) ||
                (timeInMillis > timeRangeEndingTimeInMillis && 
                    timeInMillis < endOfDayInMillis));
        } else {
            return ((timeInMillis >= timeRangeStartingTimeInMillis) &&
                    (timeInMillis <= timeRangeEndingTimeInMillis));
        }
    }
