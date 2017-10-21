    @Test
    public void testParseConvertBackslashes() {
        final boolean convert = true;
        final StringBuilder buf = new StringBuilder();
        final String pattern = "%d{HH-mm-ss} \\t ...";

        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 53);
        cal.set(Calendar.SECOND, 01);
        parse(pattern, convert, buf, cal.getTime(), 123);

        assertEquals("23-53-01 \t ...", buf.toString());
    }
