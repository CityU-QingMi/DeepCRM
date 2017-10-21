    public SimpleDateFormat getSimpleDateFormatGMT() {

        if (simpleDateFormatGMT == null) {
            simpleDateFormatGMT = new SimpleDateFormat("MMMM", Locale.ENGLISH);

            Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"),
                                                 HsqlDateTime.defaultLocale);

            cal.setLenient(false);
            simpleDateFormatGMT.setCalendar(cal);
        }

        return simpleDateFormatGMT;
    }
