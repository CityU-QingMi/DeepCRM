    @Test
    public void testSpecialChars() throws Exception {
        final PatternLayout layout = PatternLayout.newBuilder().withPattern("\\\\%level\\t%msg\\n\\t%logger\\r\\n\\f")
                .withConfiguration(ctx.getConfiguration()).build();
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        assertToByteArray("\\INFO\tHello, world!\n" +
                "\torg.apache.logging.log4j.core.layout.PatternLayoutTest\r\n" +
                "\f", layout, event);
        assertEncode("\\INFO\tHello, world!\n" +
                "\torg.apache.logging.log4j.core.layout.PatternLayoutTest\r\n" +
                "\f", layout, event);
    }
