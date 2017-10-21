    @Override
    public long getNextIncludedTime(long timeStamp) {
        if (excludeAll == true) {
            return 0;
        }

        // Call base calendar implementation first
        long baseTime = super.getNextIncludedTime(timeStamp);
        if ((baseTime > 0) && (baseTime > timeStamp)) {
            timeStamp = baseTime;
        }

        // Get timestamp for 00:00:00
        java.util.Calendar cl = getStartOfDayJavaCalendar(timeStamp);
        int wday = cl.get(java.util.Calendar.DAY_OF_WEEK);

        if (!isDayExcluded(wday)) {
            return timeStamp; // return the original value
        }

        while (isDayExcluded(wday) == true) {
            cl.add(java.util.Calendar.DATE, 1);
            wday = cl.get(java.util.Calendar.DAY_OF_WEEK);
        }

        return cl.getTime().getTime();
    }
