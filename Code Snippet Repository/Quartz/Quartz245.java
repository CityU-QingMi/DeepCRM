    public long getNextIncludedTime(long timeStamp) {

        if (timeStamp <= 0) {
            throw new IllegalArgumentException(
                    "timeStamp must be greater 0");
        }

        if (baseCalendar != null) {
            return baseCalendar.getNextIncludedTime(timeStamp);
        }

        return timeStamp;
    }
