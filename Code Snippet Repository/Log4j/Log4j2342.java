    private void testConverter(final String expected) {
        final Message msg = new SimpleMessage("Hello");
        final NdcPatternConverter converter = NdcPatternConverter.newInstance(null);
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg) //
                .build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        final String str = sb.toString();
        assertEquals(expected, str);
    }
