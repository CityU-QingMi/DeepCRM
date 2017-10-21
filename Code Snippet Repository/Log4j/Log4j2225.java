    @Test
    public void testEqualsMarkerWithMessageSubstitution() throws Exception {
        // replace "[]" with the empty string
        final PatternLayout layout = PatternLayout.newBuilder().withPattern("[%logger]%equals{[%marker]}{[]}{[%msg]}")
            .withConfiguration(ctx.getConfiguration()).build();
        // Not empty marker
        final LogEvent event1 = Log4jLogEvent.newBuilder() //
            .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
            .setLevel(Level.INFO) //
            .setMarker(MarkerManager.getMarker("TestMarker"))
            .setMessage(new SimpleMessage("Hello, world!")).build();
        final byte[] result1 = layout.toByteArray(event1);
        assertEquals("[org.apache.logging.log4j.core.layout.PatternLayoutTest][TestMarker]", new String(result1));
        // empty marker
        final LogEvent event2 = Log4jLogEvent.newBuilder() //
            .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
            .setLevel(Level.INFO)
            .setMessage(new SimpleMessage("Hello, world!")).build();
        final byte[] result2 = layout.toByteArray(event2);
        assertEquals("[org.apache.logging.log4j.core.layout.PatternLayoutTest][Hello, world!]", new String(result2));
    }
