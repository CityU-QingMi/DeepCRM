    @Test
    public void testConverter() {
        final Message msg = new SimpleMessage("Hello");
        final MdcPatternConverter converter = MdcPatternConverter.newInstance(null);
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg) //
                .build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        final String str = sb.toString();
        final String expected = "{object=Log4j, subject=I, verb=love}";
        assertTrue("Incorrect result. Expected " + expected + ", actual " + str, str.equals(expected));
    }
