    @Test
    public void testCustomPattern() {
        final List<PatternFormatter> formatters = parser.parse(customPattern);
        assertNotNull(formatters);
        final Map<String, String> mdc = new HashMap<>();
        mdc.put("loginId", "Fred");
        final Throwable t = new Throwable();
        final StackTraceElement[] elements = t.getStackTrace();
        final Log4jLogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("org.apache.logging.log4j.PatternParserTest") //
                .setMarker(MarkerManager.getMarker("TEST")) //
                .setLoggerFqcn(Logger.class.getName()) //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world")) //
                .setContextMap(mdc) //
                .setThreadName("Thread1") //
                .setSource(elements[0])
                .setTimeMillis(System.currentTimeMillis()).build();
        final StringBuilder buf = new StringBuilder();
        for (final PatternFormatter formatter : formatters) {
            formatter.format(event, buf);
        }
        final String str = buf.toString();
        final String expected = "INFO  [PatternParserTest        :100 ] - Hello, world" + Strings.LINE_SEPARATOR;
        assertTrue("Expected to end with: " + expected + ". Actual: " + str, str.endsWith(expected));
    }
