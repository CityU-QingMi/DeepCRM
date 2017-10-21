    @Test
    public void testLookupEventNonExistant() {
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()) //
                .setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        final String value = strLookup.lookup(event, ABSENT_MARKER_NAME);
        assertNull(value);
    }
