    public Calendar getCalendar() {

        if (calendar == null) {
            if (zoneString == null) {
                calendar = new GregorianCalendar();
            } else {
                TimeZone zone = TimeZone.getTimeZone(zoneString);

                calendar = new GregorianCalendar(zone);
            }
        }

        return calendar;
    }
