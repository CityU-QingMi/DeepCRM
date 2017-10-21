    private void logAndCheck() {
        final Marker marker = MarkerManager.getMarker("HEXDUMP");
        final Logger logger = loggerContextRule.getLogger(RoutesScriptAppenderTest.class);
        logger.error("Hello");
        final ListAppender listAppender = getListAppender();
        final List<LogEvent> list = listAppender.getEvents();
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events. Expected 1, got " + list.size(), list.size() == 1);
        logger.error("World");
        assertTrue("Incorrect number of events. Expected 2, got " + list.size(), list.size() == 2);
        logger.error(marker, "DEADBEEF");
        assertTrue("Incorrect number of events. Expected 3, got " + list.size(), list.size() == 3);
    }
