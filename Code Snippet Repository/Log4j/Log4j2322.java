    @Test
    public void testLevelLowerCase() {
        final Message msg = new SimpleMessage("Hello");
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg).build();
        final StringBuilder sb = new StringBuilder();
        LevelPatternConverter converter = LevelPatternConverter.newInstance(null);
        converter.format(event, sb);
        assertEquals(Level.DEBUG.toString(), sb.toString());
        final String[] opts = new String[] { "lowerCase=true" };
        converter = LevelPatternConverter.newInstance(opts);
        sb.setLength(0);
        converter.format(event, sb);
        assertEquals("debug", sb.toString());
        event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.WARN) //
                .setMessage(msg).build();
        sb.setLength(0);
        converter.format(event, sb);
        assertEquals("warn", sb.toString());
    }
