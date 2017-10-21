    @Test
    public void testLookup() {
        final Message msg = new StructuredDataMessage("Test", "This is a test", "Audit");
        final Marker eventMarker = MarkerManager.getMarker("EVENT");
        final Marker auditMarker = MarkerManager.getMarker("AUDIT").setParents(eventMarker);
        final LogEvent event = Log4jLogEvent.newBuilder().setLoggerName("MyLogger").setMarker(auditMarker)
                .setLevel(Level.DEBUG).setMessage(msg).build();
        final StringBuilder sb = new StringBuilder();
        final MarkerSimpleNamePatternConverter converter = MarkerSimpleNamePatternConverter.newInstance(null);
        converter.format(event, sb);
        assertEquals(auditMarker.getName(), sb.toString());
    }
