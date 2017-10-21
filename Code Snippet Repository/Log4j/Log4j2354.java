    @Test
    public void testReplacement() {
        ThreadContext.put("MyKey", "Apache");
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(RegexReplacementConverterTest.class.getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("This is a test")) //
                .build();
        final StringBuilder sb = new StringBuilder();
        final LoggerContext ctx = LoggerContext.getContext();
        final String[] options = new String[] {
            "%logger %msg%n", "\\.", "/"
        };
        final RegexReplacementConverter converter = RegexReplacementConverter.newInstance(ctx.getConfiguration(),
            options);
        converter.format(event, sb);
        assertEquals("org/apache/logging/log4j/core/pattern/RegexReplacementConverterTest This is a test" +
            Strings.LINE_SEPARATOR, sb.toString());
    }
