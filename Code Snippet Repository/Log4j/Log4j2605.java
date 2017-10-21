    @Test
    public void testTzParses() throws Exception {
        // Check that all Locales can parse the time formats we use
        for(final Locale locale : Locale.getAvailableLocales()) {
            final FastDateParser fdp= new FastDateParser("yyyy/MM/dd z", TimeZone.getDefault(), locale);

            for(final TimeZone tz :  new TimeZone[]{NEW_YORK, REYKJAVIK, GMT}) {
                final Calendar cal= Calendar.getInstance(tz, locale);
                cal.clear();
                cal.set(Calendar.YEAR, 2000);
                cal.set(Calendar.MONTH, 1);
                cal.set(Calendar.DAY_OF_MONTH, 10);
                final Date expected= cal.getTime();

                final Date actual = fdp.parse("2000/02/10 "+tz.getDisplayName(locale));
                Assert.assertEquals("tz:"+tz.getID()+" locale:"+locale.getDisplayName(), expected, actual);
            }
        }
    }
