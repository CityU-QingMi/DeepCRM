    @Test
    public void testEvent() {
        final org.apache.logging.log4j.Logger logger = context.getLogger("org.apache.test.LogEventFactory");
        logger.error("error message");
        final List<LogEvent> events = app.getEvents();
        assertNotNull("No events", events);
        assertEquals("Incorrect number of events. Expected 1, actual " + events.size(), 1, events.size());
        final LogEvent event = events.get(0);
        assertEquals("TestLogEventFactory wasn't used", "Test", event.getLoggerName());
    }
