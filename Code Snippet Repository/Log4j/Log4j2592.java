    @Test
    public void testParseZone() throws ParseException {
        final Calendar cal= Calendar.getInstance(NEW_YORK, Locale.US);
        cal.clear();
        cal.set(2003, Calendar.JULY, 10, 16, 33, 20);

        final DateParser fdf = getInstance(yMdHmsSZ, NEW_YORK, Locale.US);

        assertEquals(cal.getTime(), fdf.parse("2003-07-10T15:33:20.000 -0500"));
        assertEquals(cal.getTime(), fdf.parse("2003-07-10T15:33:20.000 GMT-05:00"));
        assertEquals(cal.getTime(), fdf.parse("2003-07-10T16:33:20.000 Eastern Daylight Time"));
        assertEquals(cal.getTime(), fdf.parse("2003-07-10T16:33:20.000 EDT"));

        cal.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        cal.set(2003, Calendar.FEBRUARY, 10, 9, 0, 0);

        assertEquals(cal.getTime(), fdf.parse("2003-02-10T09:00:00.000 -0300"));

        cal.setTimeZone(TimeZone.getTimeZone("GMT+5"));
        cal.set(2003, Calendar.FEBRUARY, 10, 15, 5, 6);

        assertEquals(cal.getTime(), fdf.parse("2003-02-10T15:05:06.000 +0500"));
    }
