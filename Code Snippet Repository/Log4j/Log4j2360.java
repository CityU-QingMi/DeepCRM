    @Test
    public void testFull2() {
        final RootThrowablePatternConverter converter = RootThrowablePatternConverter.newInstance(null, null);
        Throwable parent;
        try {
            try {
                throw new NullPointerException("null pointer");
            } catch (final NullPointerException e) {
                throw new IllegalArgumentException("IllegalArgument", e);
            }
        } catch (final IllegalArgumentException e) {
            parent = e;
        }
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
