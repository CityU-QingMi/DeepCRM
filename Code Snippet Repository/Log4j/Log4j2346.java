    @Test
    public void testBadPattern() {
        final Calendar cal = Calendar.getInstance();
        cal.set(2001, Calendar.FEBRUARY, 3, 4, 5, 6);
        cal.set(Calendar.MILLISECOND, 789);
        final long timestamp = cal.getTimeInMillis();

        final List<PatternFormatter> formatters = parser.parse(badPattern);
        assertNotNull(formatters);
        final Throwable t = new Throwable();
        final StackTraceElement[] elements = t.getStackTrace();
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("a.b.c") //
                .setLoggerFqcn(Logger.class.getName()) //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world")) //
                .setThreadName("Thread1") //
                .setSource(elements[0]) //
                .setTimeMillis(timestamp) //
                .build();
        final StringBuilder buf = new StringBuilder();
        for (final PatternFormatter formatter : formatters) {
            formatter.format(event, buf);
        }
        final String str = buf.toString();

        // eats all characters until the closing '}' character
        final String expected = "[2001-02-03 04:05:06,789] - Hello, world";
        assertTrue("Expected to start with: " + expected + ". Actual: " + str, str.startsWith(expected));
    }
