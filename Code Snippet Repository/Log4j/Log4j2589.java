    @Test
    public void testLowYearPadding() throws ParseException {
        final DateParser parser = getInstance(YMD_SLASH);
        final Calendar cal = Calendar.getInstance();
        cal.clear();

        cal.set(1, Calendar.JANUARY, 1);
        assertEquals(cal.getTime(), parser.parse("0001/01/01"));
        cal.set(10, Calendar.JANUARY, 1);
        assertEquals(cal.getTime(), parser.parse("0010/01/01"));
        cal.set(100, Calendar.JANUARY, 1);
        assertEquals(cal.getTime(), parser.parse("0100/01/01"));
        cal.set(999, Calendar.JANUARY, 1);
        assertEquals(cal.getTime(), parser.parse("0999/01/01"));
    }
