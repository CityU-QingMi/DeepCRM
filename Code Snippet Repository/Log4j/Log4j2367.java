    @Test
    public void testShortLocalizedMessage() {
        final String[] options = { "short.localizedMessage" };
        final ThrowablePatternConverter converter = ThrowablePatternConverter.newInstance(null, options);
        final Throwable parent = new LocalizedException();
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("testLogger") //
                .setLoggerFqcn(this.getClass().getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("test exception")) //
                .setThrown(parent).build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        final String result = sb.toString();
        assertEquals("The messages should be same", "I am localized.", result);
    }
