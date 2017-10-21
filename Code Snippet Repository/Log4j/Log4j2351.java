    @Test
    public void testParseDontConvertBackslashes() {
        final boolean convert = false;
        final StringBuilder buf = new StringBuilder();
        final String pattern = "%d{HH-mm-ss} \\t---";

        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 24);
        cal.set(Calendar.SECOND, 59);
        parse(pattern, convert, buf, cal.getTime(), 3);

        assertEquals("13-24-59 \\t---", buf.toString());
    }
