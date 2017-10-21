    private Calendar getEraStart(int year, final TimeZone zone, final Locale locale) {
        final Calendar cal = Calendar.getInstance(zone, locale);
        cal.clear();

        // http://docs.oracle.com/javase/6/docs/technotes/guides/intl/calendar.doc.html
        if (locale.equals(FastDateParser.JAPANESE_IMPERIAL)) {
            if(year < 1868) {
                cal.set(Calendar.ERA, 0);
                cal.set(Calendar.YEAR, 1868-year);
            }
        }
        else {
            if (year < 0) {
                cal.set(Calendar.ERA, GregorianCalendar.BC);
                year= -year;
            }
            cal.set(Calendar.YEAR, year/100 * 100);
        }
        return cal;
    }
