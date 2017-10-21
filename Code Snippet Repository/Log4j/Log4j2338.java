    @Test
    public void testPatternAndParameterizedMessageDateLookup() throws Exception {
        final MessagePatternConverter converter = MessagePatternConverter.newInstance(null, null);
        final Message msg = new ParameterizedMessage("${date:now:buhu}");
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg).build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        assertEquals("Unexpected result", "${date:now:buhu}", sb.toString());
    }
