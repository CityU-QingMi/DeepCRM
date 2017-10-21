    @Test
    public void testFull1() {
        final RootThrowablePatternConverter converter = RootThrowablePatternConverter.newInstance(null, null);
        final Throwable cause = new NullPointerException("null pointer");
        final Throwable parent = new IllegalArgumentException("IllegalArgument", cause);
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("testLogger") //
                .setLoggerFqcn(this.getClass().getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("test exception")) //
                .setThrown(parent).build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        final String result = sb.toString();
        // System.out.print(result);
        assertTrue("Missing Exception",
                result.contains("Wrapped by: java.lang.IllegalArgumentException: IllegalArgument"));
        assertTrue("Incorrect start of msg", result.startsWith("java.lang.NullPointerException: null pointer"));
    }
