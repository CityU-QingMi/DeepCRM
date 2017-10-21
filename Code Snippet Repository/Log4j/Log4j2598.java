    @Test
    public void testParseOffset() {
        final DateParser parser = getInstance(YMD_SLASH);
        final Date date = parser.parse("Today is 2015/07/04", new ParsePosition(9));

        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2015, Calendar.JULY, 4);
        Assert.assertEquals(cal.getTime(), date);
    }
