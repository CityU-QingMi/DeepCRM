    private void test(final Map<String, String> contextMap, final String expected, final String[] options) {
        final LogEvent event = Log4jLogEvent.newBuilder()
                .setLoggerName("MyLogger")
                .setLevel(Level.DEBUG)
                .setMessage(new SimpleMessage("Hello"))
                .setContextMap(contextMap)
                .build();
        final StringBuilder sb = new StringBuilder();
        final Log4j1MdcPatternConverter converter = Log4j1MdcPatternConverter.newInstance(options);
        converter.format(event, sb);
        assertEquals(expected, sb.toString());
    }
