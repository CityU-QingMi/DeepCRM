    @Test
    public void testJsonEscaping() throws Exception {
        final LogEvent event = Log4jLogEvent.newBuilder()
            .setLoggerName(getClass().getName())
            .setLevel(Level.DEBUG)
            .setMessage(new SimpleMessage("This string contains \"quotes\" and \\ backslash and \u001F control"))
            .build();
        final String expected = "This string contains \\\"quotes\\\" and \\\\ backslash and \\u001F control";
        final StringBuilder sb = new StringBuilder();
        final LoggerContext ctx = LoggerContext.getContext();
        final String[] options = new String[]{"%msg", "JSON"};
        final EncodingPatternConverter converter = EncodingPatternConverter.newInstance(ctx.getConfiguration(), options);

        assertNotNull("Error creating converter", converter);
        converter.format(event, sb);

        assertEquals(expected, sb.toString());
    }
