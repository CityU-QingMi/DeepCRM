    @Test
    public void testShortOptionWithSuffix() {
        final String packageName = "org.apache.logging.log4j.core.pattern.";
        final String[] options = { "short.className", "suffix(test suffix)" };
        final ThrowablePatternConverter converter = ThrowablePatternConverter.newInstance(null, options);
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
        assertTrue("Each line should end with suffix", everyLineEndsWith(result, "test suffix"));
    }
