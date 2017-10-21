    @Test
    public void testOverMaxLength21WithEllipsis() throws Exception {
        final Message message = new SimpleMessage("012345678901234567890123456789");
        final LogEvent event = Log4jLogEvent.newBuilder()
            .setLoggerName("MyLogger")
            .setLevel(Level.DEBUG)
            .setMessage(message)
            .build();
        final StringBuilder sb = new StringBuilder();
        MaxLengthConverter.newInstance(null, new String[]{"%m", "21"}).format(event, sb);
        assertEquals("012345678901234567890...", sb.toString());
    }
