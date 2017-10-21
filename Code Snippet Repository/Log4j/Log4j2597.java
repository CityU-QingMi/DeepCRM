    @Test
    public void testLang1121() throws ParseException {
        final TimeZone kst = TimeZone.getTimeZone("KST");
        final DateParser fdp = getInstance("yyyyMMdd", kst, Locale.KOREA);

        try {
            fdp.parse("2015");
            Assert.fail("expected parse exception");
        } catch (final ParseException pe) {
            // expected parse exception
        }

        // Wed Apr 29 00:00:00 KST 2015
        Date actual = fdp.parse("20150429");
        final Calendar cal = Calendar.getInstance(kst, Locale.KOREA);
        cal.clear();
        cal.set(2015, 3, 29);
        Date expected = cal.getTime();
        Assert.assertEquals(expected, actual);

        final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        df.setTimeZone(kst);
        expected = df.parse("20150429113100");

        // Thu Mar 16 00:00:00 KST 81724
        actual = fdp.parse("20150429113100");
        Assert.assertEquals(expected, actual);
    }
