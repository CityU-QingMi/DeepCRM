    public Calendar getCalendarGMT() {

        if (calendarGMT == null) {
            calendarGMT = new GregorianCalendar(TimeZone.getTimeZone("GMT"),
                                                HsqlDateTime.defaultLocale);

            calendarGMT.setLenient(false);
        }

        return calendarGMT;
    }
