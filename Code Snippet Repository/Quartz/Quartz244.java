    public boolean isTimeIncluded(long timeStamp) {

        if (timeStamp <= 0) {
            throw new IllegalArgumentException(
                    "timeStamp must be greater 0");
        }

        if (baseCalendar != null) {
            if (baseCalendar.isTimeIncluded(timeStamp) == false) { return false; }
        }

        return true;
    }
