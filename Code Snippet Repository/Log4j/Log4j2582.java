    @Test
    public void testJpLocales() {

        final Calendar cal= Calendar.getInstance(GMT);
        cal.clear();
        cal.set(2003, Calendar.FEBRUARY, 10);
        cal.set(Calendar.ERA, GregorianCalendar.BC);

        final Locale locale = LocaleUtils.toLocale("zh"); {
            // ja_JP_JP cannot handle dates before 1868 properly

            final SimpleDateFormat sdf = new SimpleDateFormat(LONG_FORMAT, locale);
            final DateParser fdf = getInstance(LONG_FORMAT, locale);

            try {
                checkParse(locale, cal, sdf, fdf);
            } catch(final ParseException ex) {
                Assert.fail("Locale "+locale+ " failed with "+LONG_FORMAT+"\n" + trimMessage(ex.toString()));
            }
        }
    }
