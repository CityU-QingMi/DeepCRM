    public static TimestampData nextDayOfWeek(Session session,
            TimestampData d, int day) {

        Calendar cal = session.getCalendarGMT();

        cal.setTimeInMillis(d.getMillis());

        int start = cal.get(Calendar.DAY_OF_WEEK);

        if (start >= day) {
            day += 7;
        }

        int diff = day - start;

        cal.add(Calendar.DAY_OF_MONTH, diff);

        long millis = cal.getTimeInMillis();

        millis = HsqlDateTime.getNormalisedDate(cal, millis);

        return new TimestampData(millis / 1000);
    }
