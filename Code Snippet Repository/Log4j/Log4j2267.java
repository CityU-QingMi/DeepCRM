    @Test
    public void testLookupEventExistant() {
        final Marker marker = MarkerManager.getMarker(markerName);
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()) //
                .setMarker(marker) //
                .setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world!")).build();
        final String value = strLookup.lookup(event, marker.getName());
        assertEquals(markerName, value);
    }
