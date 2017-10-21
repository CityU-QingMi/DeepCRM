    @Test
    public void testPatternTruncateFromEnd() {
        final List<PatternFormatter> formatters = parser.parse(patternTruncateFromEnd);
        assertNotNull(formatters);
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("org.apache.logging.log4j.PatternParserTest") //
                .setLoggerFqcn(Logger.class.getName()) //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world")) //
                .setThreadName("Thread1") //
                .setTimeMillis(System.currentTimeMillis()) //
                .build();
        final StringBuilder buf = new StringBuilder();
        for (final PatternFormatter formatter : formatters) {
            formatter.format(event, buf);
        }
        final String str = buf.toString();
        final String expected = "INFO  org.a Hello, world" + Strings.LINE_SEPARATOR;
        assertTrue("Expected to end with: " + expected + ". Actual: " + str, str.endsWith(expected));
    }
