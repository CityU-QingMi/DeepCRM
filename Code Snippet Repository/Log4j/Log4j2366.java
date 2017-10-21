    @Test
    public void testShortLineNumber() {
        final String[] options = { "short.lineNumber" };
        final ThrowablePatternConverter converter = ThrowablePatternConverter.newInstance(null, options);
        final Throwable cause = new NullPointerException("null pointer");
        final Throwable parent = new IllegalArgumentException("IllegalArgument", cause);
        final StackTraceElement top = parent.getStackTrace()[0];
        final int expectedLineNumber = top.getLineNumber();

        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("testLogger") //
                .setLoggerFqcn(this.getClass().getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("test exception")) //
                .setThrown(parent).build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        final String result = sb.toString();
        assertTrue("The line numbers should be same", expectedLineNumber == Integer.parseInt(result));
    }
