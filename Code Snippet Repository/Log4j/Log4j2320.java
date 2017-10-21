    @Test
    public void testNoAnsiNonEmpty() {
        final String[] options = {"%-5level: %msg", PatternParser.DISABLE_ANSI + "=true"};
        final HighlightConverter converter = HighlightConverter.newInstance(null, options);

        final LogEvent event = Log4jLogEvent.newBuilder().setLevel(Level.INFO).setLoggerName("a.b.c").setMessage(
                new SimpleMessage("message in a bottle")).build();
        final StringBuilder buffer = new StringBuilder();
        converter.format(event, buffer);
        assertEquals("INFO : message in a bottle", buffer.toString());
    }
