    @Test
    public void testRegex() throws Exception {
        final PatternLayout layout = PatternLayout.newBuilder().withPattern(regexPattern)
                .withConfiguration(ctx.getConfiguration()).build();
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        assertToByteArray("org/apache/logging/log4j/core/layout/PatternLayoutTest Hello, world!", layout, event);
        assertEncode("org/apache/logging/log4j/core/layout/PatternLayoutTest Hello, world!", layout, event);
    }
