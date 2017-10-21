    @Test
    public void testUnderMaxLength() throws Exception {
        final Message message = new SimpleMessage("0123456789");
        final LogEvent event = Log4jLogEvent.newBuilder()
            .setLoggerName("MyLogger")
            .setLevel(Level.DEBUG)
            .setMessage(message)
            .build();
        final StringBuilder sb = new StringBuilder();
        converter.format(event, sb);
        assertEquals("0123456789", sb.toString());
    }
