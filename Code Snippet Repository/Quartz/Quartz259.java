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
        int day = cl.get(java.util.Calendar.DAY_OF_MONTH);

        if (!isDayExcluded(day)) {
            return timeStamp; // return the original value
        }

        while (isDayExcluded(day) == true) {
            cl.add(java.util.Calendar.DATE, 1);
            day = cl.get(java.util.Calendar.DAY_OF_MONTH);
        }

        return cl.getTime().getTime();
    }
