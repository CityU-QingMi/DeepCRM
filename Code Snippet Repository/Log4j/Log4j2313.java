    @Test
    public void testFull() {
        final ExtendedThrowablePatternConverter converter = ExtendedThrowablePatternConverter.newInstance(null, null);
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
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        parent.printStackTrace(pw);
        String result = sb.toString();
        result = result.replaceAll(" ~?\\[.*\\]", Strings.EMPTY);
        final String expected = sw.toString().replaceAll("\r", Strings.EMPTY);
        assertEquals(expected, result);
    }
