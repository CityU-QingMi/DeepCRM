    private void testConverter(final String expected) {
        final Log4j1NdcPatternConverter converter = Log4j1NdcPatternConverter.newInstance(null);
        final LogEvent event = Log4jLogEvent.newBuilder()
                .setLoggerName("MyLogger")
                .setLevel(Level.DEBUG)
                .setMessage(new SimpleMessage("Hello"))
                .build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        assertEquals(expected, sb.toString());
    }
