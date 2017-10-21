    @Test
    public void testFormatDateStringBuilderIso8601WithPeriod() {
        final String[] pattern = {FixedDateFormat.FixedFormat.ISO8601_PERIOD.name()};
        final DatePatternConverter converter = DatePatternConverter.newInstance(pattern);
        final StringBuilder sb = new StringBuilder();
        converter.format(date(2001, 1, 1), sb);

        final String expected = "2001-02-01T14:15:16.123";
        assertEquals(expected, sb.toString());
    }
