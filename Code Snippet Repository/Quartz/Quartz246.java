    @Override
    public long getNextIncludedTime(long timeInMillis) {
        long nextIncludedTime = timeInMillis + 1; //plus on millisecond
        
        while (!isTimeIncluded(nextIncludedTime)) {

            //If the time is in a range excluded by this calendar, we can
            // move to the end of the excluded time range and continue testing
            // from there. Otherwise, if nextIncludedTime is excluded by the
            // baseCalendar, ask it the next time it includes and begin testing
            // from there. Failing this, add one millisecond and continue
            // testing.
            if (cronExpression.isSatisfiedBy(new Date(nextIncludedTime))) {
                nextIncludedTime = cronExpression.getNextInvalidTimeAfter(
                        new Date(nextIncludedTime)).getTime();
            } else if ((getBaseCalendar() != null) && 
                    (!getBaseCalendar().isTimeIncluded(nextIncludedTime))){
                nextIncludedTime = 
                    getBaseCalendar().getNextIncludedTime(nextIncludedTime);
            } else {
                nextIncludedTime++;
            }
        }
        
        return nextIncludedTime;
    }
