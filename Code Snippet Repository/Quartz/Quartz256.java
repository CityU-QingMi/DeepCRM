    @Override
    public long getNextIncludedTime(long timeStamp) {

        // Call base calendar implementation first
        long baseTime = super.getNextIncludedTime(timeStamp);
        if ((baseTime > 0) && (baseTime > timeStamp)) {
            timeStamp = baseTime;
        }

        // Get timestamp for 00:00:00
        java.util.Calendar day = getStartOfDayJavaCalendar(timeStamp);
        while (isTimeIncluded(day.getTime().getTime()) == false) {
            day.add(java.util.Calendar.DATE, 1);
        }

        return day.getTime().getTime();
    }
