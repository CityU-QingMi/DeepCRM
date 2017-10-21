    private void testReplacement(final String tag, final String expectedValue) {
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(VariablesNotEmptyReplacementConverterTest.class.getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("This is a test")) //
                .build();
        final StringBuilder sb = new StringBuilder();
        final LoggerContext ctx = LoggerContext.getContext();
        final String[] options = new String[] { "[" + tag + "]" };
        final VariablesNotEmptyReplacementConverter converter = VariablesNotEmptyReplacementConverter
                .newInstance(ctx.getConfiguration(), options);
        converter.format(event, sb);
        assertEquals(expectedValue, sb.toString());
    }
