    private void testNestedPatternHighlight(final Level level, final String expectedStart) {
        final List<PatternFormatter> formatters = parser.parse(nestedPatternHighlight);
        assertNotNull(formatters);
        final Throwable t = new Throwable();
        t.getStackTrace();
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("org.apache.logging.log4j.PatternParserTest") //
                .setMarker(MarkerManager.getMarker("TEST")) //
                .setLoggerFqcn(Logger.class.getName()) //
                .setLevel(level) //
                .setMessage(new SimpleMessage("Hello, world")) //
                .setThreadName("Thread1") //
                .setSource(/*stackTraceElement[0]*/ null) //
                .setTimeMillis(System.currentTimeMillis()) //
                .build();
        final StringBuilder buf = new StringBuilder();
        for (final PatternFormatter formatter : formatters) {
            formatter.format(event, buf);
        }
        final String str = buf.toString();
        final String expectedEnd = String.format("] %-5s: Hello, world%s\u001B[m", level, Strings.LINE_SEPARATOR);
        assertTrue("Expected to start with: " + expectedStart + ". Actual: " + str, str.startsWith(expectedStart));
        assertTrue("Expected to end with: \"" + expectedEnd + "\". Actual: \"" + str, str.endsWith(expectedEnd));
    }
