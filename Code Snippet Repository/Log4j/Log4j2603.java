    private void validateSdfFormatFdpParseEquality(final String format, final Locale locale, final TimeZone tz, final DateParser fdp, final Date in, final int year, final Date cs) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setTimeZone(tz);
        if (format.equals(SHORT_FORMAT)) {
            sdf.set2DigitYearStart( cs );
        }
        final String fmt = sdf.format(in);
        try {
            final Date out = fdp.parse(fmt);
            assertEquals(locale.toString()+" "+in+" "+ format+ " "+tz.getID(), in, out);
        } catch (final ParseException pe) {
            if (year >= 1868 || !locale.getCountry().equals("JP")) {// LANG-978
                throw pe;
            }
        }
    }
