    public static long convertMillisFromCalendar(Calendar clendarGMT,
            Calendar calendar, long millis) {

        synchronized (clendarGMT) {
            synchronized (calendar) {
                clendarGMT.clear();
                calendar.setTimeInMillis(millis);
                clendarGMT.set(calendar.get(Calendar.YEAR),
                               calendar.get(Calendar.MONTH),
                               calendar.get(Calendar.DAY_OF_MONTH),
                               calendar.get(Calendar.HOUR_OF_DAY),
                               calendar.get(Calendar.MINUTE),
                               calendar.get(Calendar.SECOND));

                return clendarGMT.getTimeInMillis();
            }
        }
    }
