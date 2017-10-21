    @Test
    public void testAnsiNonEmpty() {
        final String[] options = {"%-5level: %msg", PatternParser.NO_CONSOLE_NO_ANSI + "=false, " + PatternParser.DISABLE_ANSI + "=false"};
        final HighlightConverter converter = HighlightConverter.newInstance(null, options);

        final LogEvent event = Log4jLogEvent.newBuilder().setLevel(Level.INFO).setLoggerName("a.b.c").setMessage(
                new SimpleMessage("message in a bottle")).build();
        final StringBuilder buffer = new StringBuilder();
        converter.format(event, buffer);
        assertEquals("\u001B[32mINFO : message in a bottle\u001B[m", buffer.toString());
    }
