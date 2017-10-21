    @Test
    public void testPattern() throws Exception {
        final MessagePatternConverter converter = MessagePatternConverter.newInstance(null, null);
        Message msg = new SimpleMessage("Hello!");
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg).build();
        StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        assertEquals("Unexpected result", "Hello!", sb.toString());
        event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(null).build();
        sb = new StringBuilder();
        converter.format(event, sb);
        assertEquals("Incorrect length: " + sb, 0, sb.length());
        msg = new SimpleMessage(null);
        event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg).build();
        sb = new StringBuilder();
        converter.format(event, sb);
        assertEquals("Incorrect length: " + sb, 4, sb.length());
    }
