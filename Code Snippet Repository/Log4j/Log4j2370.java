    @Test
    public void testFullWithSuffix() {
        final String[] options = { "full", "suffix(test suffix)" };
        final ThrowablePatternConverter converter = ThrowablePatternConverter.newInstance(null, options);
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
        assertTrue("Each line of full stack trace should end with the specified suffix", everyLineEndsWith(result, "test suffix"));
    }
