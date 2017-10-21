    @Test
    public void testFormatDateStringBuilderIso8601BasicWithPeriod() {
        final String[] pattern = {FixedDateFormat.FixedFormat.ISO8601_BASIC_PERIOD.name()};
        final DatePatternConverter converter = DatePatternConverter.newInstance(pattern);
        final StringBuilder sb = new StringBuilder();
        converter.format(date(2001, 1, 1), sb);

        final String expected = "20010201T141516.123";
        assertEquals(expected, sb.toString());
    }
