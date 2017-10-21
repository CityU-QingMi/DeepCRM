    @Test
    public void testEqualsEmptyMarker() throws Exception {
        // replace "[]" with the empty string
        final PatternLayout layout = PatternLayout.newBuilder().withPattern("[%logger]%equals{[%marker]}{[]}{} %msg")
                .withConfiguration(ctx.getConfiguration()).build();
        // Not empty marker
        final LogEvent event1 = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMarker(MarkerManager.getMarker("TestMarker")) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        assertToByteArray("[org.apache.logging.log4j.core.layout.PatternLayoutTest][TestMarker] Hello, world!", layout,
                event1);
        assertEncode("[org.apache.logging.log4j.core.layout.PatternLayoutTest][TestMarker] Hello, world!", layout,
                event1);
        // empty marker
        final LogEvent event2 = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        assertToByteArray("[org.apache.logging.log4j.core.layout.PatternLayoutTest] Hello, world!", layout, event2);
        assertEncode("[org.apache.logging.log4j.core.layout.PatternLayoutTest] Hello, world!", layout, event2);
    }
