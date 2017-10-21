    public static TimestampData addMonths(Session session,
                                          TimestampData source, int months) {

        int      n   = source.getNanos();
        Calendar cal = session.getCalendarGMT();

        HsqlDateTime.setTimeInMillis(cal, source.getSeconds() * 1000);
        cal.add(Calendar.MONTH, months);

        TimestampData ts = new TimestampData(cal.getTimeInMillis() / 1000, n,
                                             source.getZone());

        return ts;
    }
