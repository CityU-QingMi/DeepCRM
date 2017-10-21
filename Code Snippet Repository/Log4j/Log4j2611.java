    @Test
    public void testTimeZoneStrategyPattern() {
        for(final Locale locale : Locale.getAvailableLocales()) {
            final FastDateParser parser = new FastDateParser("z", TimeZone.getDefault(), locale);
            final String[][] zones = DateFormatSymbols.getInstance(locale).getZoneStrings();
            for(final String[] zone :  zones) {
                for(int t = 1; t<zone.length; ++t) {
                    final String tzDisplay = zone[t];
                    if (tzDisplay == null) {
                        break;
                    }
                    try {
                        parser.parse(tzDisplay);
                    }
                    catch(final Exception ex) {
                        Assert.fail("'" + tzDisplay + "'"
                                + " Locale: '" + locale.getDisplayName() + "'"
                                + " TimeZone: " + zone[0]
                                + " offset: " + t
                                + " defaultLocale: " + Locale.getDefault()
                                + " defaultTimeZone: " + TimeZone.getDefault().getDisplayName()
                                );
                    }
                }
            }
        }
    }
