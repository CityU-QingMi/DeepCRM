    private void testParseSubstitution(final String substitution, final String expected) {
        final LogEvent event = Log4jLogEvent.newBuilder()
            .setLoggerName(EqualsReplacementConverterTest.class.getName())
            .setLevel(Level.DEBUG)
            .setMessage(new SimpleMessage(TEST_MESSAGE))
            .build();
        final LoggerContext ctx = LoggerContext.getContext();
        final EqualsReplacementConverter converter = EqualsReplacementConverter.newInstance(ctx.getConfiguration(),
            new String[]{"[%marker]", "[]", substitution});

        final StringBuilder sb = new StringBuilder();
        converter.parseSubstitution(event, sb);
        final String actual = sb.toString();
        assertEquals(expected, actual);
    }
