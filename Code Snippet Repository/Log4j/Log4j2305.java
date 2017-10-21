    private void testReplacement(final String tag, final String expectedValue) {
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(EqualsIgnoreCaseReplacementConverterTest.class.getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("This is a test")) //
                .build();
        final StringBuilder sb = new StringBuilder();
        final LoggerContext ctx = LoggerContext.getContext();
        final String[] options = new String[] { "aaa[" + tag + "]zzz", "AAA[]ZZZ", expectedValue };
        final EqualsIgnoreCaseReplacementConverter converter = EqualsIgnoreCaseReplacementConverter
                .newInstance(ctx.getConfiguration(), options);
        converter.format(event, sb);
        assertEquals(expectedValue, sb.toString());
    }
