    @Test
    public void testFiltering() {
        final String packages = "filters(org.junit, org.apache.maven, sun.reflect, java.lang.reflect)";
        final String[] options = {packages};
        final ExtendedThrowablePatternConverter converter = ExtendedThrowablePatternConverter.newInstance(null, options);
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
        assertTrue("No suppressed lines", result.contains(" suppressed "));
    }
