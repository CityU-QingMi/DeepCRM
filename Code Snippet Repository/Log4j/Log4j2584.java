    @Test
    public void testParseNumerics() throws ParseException {
        final Calendar cal= Calendar.getInstance(NEW_YORK, Locale.US);
        cal.clear();
        cal.set(2003, Calendar.FEBRUARY, 10, 15, 33, 20);
        cal.set(Calendar.MILLISECOND, 989);

        final DateParser fdf = getInstance("yyyyMMddHHmmssSSS", NEW_YORK, Locale.US);
        assertEquals(cal.getTime(), fdf.parse("20030210153320989"));
    }
