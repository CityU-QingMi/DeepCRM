    private DateFormat[] getDateFormats(Locale locale) {
        DateFormat dt1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, locale);
        DateFormat dt2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale);
        DateFormat dt3 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);

        DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        DateFormat d2 = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        DateFormat d3 = DateFormat.getDateInstance(DateFormat.LONG, locale);

        DateFormat rfc3339         = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat rfc3339dateOnly = new SimpleDateFormat("yyyy-MM-dd");

        return new DateFormat[]{dt1, dt2, dt3, rfc3339, d1, d2, d3, rfc3339dateOnly};
    }
