    private void testReplacement(final String expectedValue, final String[] options) {
        final LogEvent event = Log4jLogEvent.newBuilder() //
            .setLoggerName(EqualsReplacementConverterTest.class.getName()) //
            .setLevel(Level.DEBUG) //
            .setMessage(new SimpleMessage(TEST_MESSAGE)) //
            .build();
        final StringBuilder sb = new StringBuilder();
        final LoggerContext ctx = LoggerContext.getContext();
        final EqualsReplacementConverter converter = EqualsReplacementConverter.newInstance(ctx.getConfiguration(),
            options);
        converter.format(event, sb);
        assertEquals(expectedValue, sb.toString());
    }
