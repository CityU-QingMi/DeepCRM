    protected FastDateParser(final String pattern, final TimeZone timeZone, final Locale locale, final Date centuryStart) {
        this.pattern = pattern;
        this.timeZone = timeZone;
        this.locale = locale;

        final Calendar definingCalendar = Calendar.getInstance(timeZone, locale);

        int centuryStartYear;
        if(centuryStart!=null) {
            definingCalendar.setTime(centuryStart);
            centuryStartYear= definingCalendar.get(Calendar.YEAR);
        }
        else if(locale.equals(JAPANESE_IMPERIAL)) {
            centuryStartYear= 0;
        }
        else {
            // from 80 years ago to 20 years from now
            definingCalendar.setTime(new Date());
            centuryStartYear= definingCalendar.get(Calendar.YEAR)-80;
        }
        century= centuryStartYear / 100 * 100;
        startYear= centuryStartYear - century;

        init(definingCalendar);
    }
