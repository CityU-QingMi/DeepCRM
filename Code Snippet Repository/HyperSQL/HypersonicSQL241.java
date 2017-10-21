    public static long convertMillisToCalendar(Calendar calendar,
            long millis) {

        synchronized (tempCalGMT) {
            synchronized (calendar) {
                calendar.clear();
                tempCalGMT.setTimeInMillis(millis);
                calendar.set(tempCalGMT.get(Calendar.YEAR),
                             tempCalGMT.get(Calendar.MONTH),
                             tempCalGMT.get(Calendar.DAY_OF_MONTH),
                             tempCalGMT.get(Calendar.HOUR_OF_DAY),
                             tempCalGMT.get(Calendar.MINUTE),
                             tempCalGMT.get(Calendar.SECOND));

                return calendar.getTimeInMillis();
            }
        }
    }
