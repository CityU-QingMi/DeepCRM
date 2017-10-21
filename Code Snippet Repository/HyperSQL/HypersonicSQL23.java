    public Calendar getCalendar() {

        if (calendar == null) {
            TimeZone zone = TimeZone.getTimeZone(zoneString);

            calendar = new GregorianCalendar(zone);
        }

        return calendar;
    }
