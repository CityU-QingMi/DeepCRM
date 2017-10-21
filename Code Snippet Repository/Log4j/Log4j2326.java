    @Test
    public void testConverter() {

        final StringMapMessage msg = new StringMapMessage();
        msg.put("subject", "I");
        msg.put("verb", "love");
        msg.put("object", "Log4j");
        final MapPatternConverter converter = MapPatternConverter.newInstance(null);
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("MyLogger") //
                .setLevel(Level.DEBUG) //
                .setMessage(msg) //
                .build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        final String str = sb.toString();
        String expected = "subject=I";
        assertTrue("Missing or incorrect subject. Expected " + expected + ", actual " + str, str.contains(expected));
        expected = "verb=love";
        assertTrue("Missing or incorrect verb", str.contains(expected));
        expected = "object=Log4j";
        assertTrue("Missing or incorrect object", str.contains(expected));

        assertEquals("{object=Log4j, subject=I, verb=love}", str);
    }
