    public Object addMonthsSpecial(Session session, Object dateTime,
                                   int months) {

        TimestampData ts     = (TimestampData) dateTime;
        Calendar      cal    = session.getCalendarGMT();
        long          millis = (ts.getSeconds() + ts.getZone()) * 1000;
        boolean       lastDay;

        HsqlDateTime.setTimeInMillis(cal, millis);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);

        lastDay = millis == cal.getTimeInMillis();

        HsqlDateTime.setTimeInMillis(cal, millis);
        cal.add(Calendar.MONTH, months);

        if (lastDay) {
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        millis = cal.getTimeInMillis();

        return new TimestampData(millis / 1000, 0, 0);
    }
