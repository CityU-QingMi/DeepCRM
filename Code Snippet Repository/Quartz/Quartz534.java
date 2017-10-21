    @Override
    public Date getFireTimeAfter(Date afterTime) {
        if (complete) {
            return null;
        }

        if ((timesTriggered > repeatCount)
                && (repeatCount != REPEAT_INDEFINITELY)) {
            return null;
        }

        if (afterTime == null) {
            afterTime = new Date();
        }

        if (repeatCount == 0 && afterTime.compareTo(getStartTime()) >= 0) {
            return null;
        }

        long startMillis = getStartTime().getTime();
        long afterMillis = afterTime.getTime();
        long endMillis = (getEndTime() == null) ? Long.MAX_VALUE : getEndTime()
                .getTime();

        if (endMillis <= afterMillis) {
            return null;
        }

        if (afterMillis < startMillis) {
            return new Date(startMillis);
        }

        long numberOfTimesExecuted = ((afterMillis - startMillis) / repeatInterval) + 1;

        if ((numberOfTimesExecuted > repeatCount) && 
            (repeatCount != REPEAT_INDEFINITELY)) {
            return null;
        }

        Date time = new Date(startMillis + (numberOfTimesExecuted * repeatInterval));

        if (endMillis <= time.getTime()) {
            return null;
        }

        return time;
    }
