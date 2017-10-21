    @Test
    public void testConverterWithKey() {

        final StringMapMessage msg = new StringMapMessage();
        msg.put("subject", "I");
        msg.put("verb", "love");
        msg.put("object", "Log4j");
        final MapPatternConverter converter = MapPatternConverter.newInstance(new String[] {"object"});
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg) //
                .build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        final String str = sb.toString();
        final String expected = "Log4j";
        assertEquals(expected, str);
    }
