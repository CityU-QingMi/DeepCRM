    @Test
    public void testConverterWithKey() {
        final Message msg = new SimpleMessage("Hello");
        final String [] options = new String[] {"object"};
        final MdcPatternConverter converter = MdcPatternConverter.newInstance(options);
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
