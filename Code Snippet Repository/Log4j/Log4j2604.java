    public void testParses() throws Exception {
        for(final String format : new String[]{LONG_FORMAT, SHORT_FORMAT}) {
            for(final Locale locale : Locale.getAvailableLocales()) {
                for(final TimeZone tz :  new TimeZone[]{NEW_YORK, REYKJAVIK, GMT}) {
                     for(final int year : new int[]{2003, 1940, 1868, 1867, 1, -1, -1940}) {
                        final Calendar cal= getEraStart(year, tz, locale);
                        final Date centuryStart= cal.getTime();

                        cal.set(Calendar.MONTH, 1);
                        cal.set(Calendar.DAY_OF_MONTH, 10);
                        final Date in= cal.getTime();

                        final FastDateParser fdp= new FastDateParser(format, tz, locale, centuryStart);
                        validateSdfFormatFdpParseEquality(format, locale, tz, fdp, in, year, centuryStart);
                    }
                }
            }
        }
    }
