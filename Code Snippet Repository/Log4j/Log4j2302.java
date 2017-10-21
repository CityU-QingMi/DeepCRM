    @Test
    public void testFormatDateStringBuilderOriginalPattern() {
        final String[] pattern = {"yyyy/MM/dd HH-mm-ss.SSS"};
        final DatePatternConverter converter = DatePatternConverter.newInstance(pattern);
        final StringBuilder sb = new StringBuilder();
        converter.format(date(2001, 1, 1), sb);

        final String expected = "2001/02/01 14-15-16.123";
        assertEquals(expected, sb.toString());
    }
