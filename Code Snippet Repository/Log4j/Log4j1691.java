    @Test
    public void testFailover() {
        logger.error("This is a test");
        List<LogEvent> events = app.getEvents();
        assertNotNull(events);
        assertEquals("Incorrect number of events. Should be 1 is " + events.size(), events.size(), 1);
        app.clear();
        logger.error("This is a test");
        events = app.getEvents();
        assertNotNull(events);
        assertEquals("Incorrect number of events. Should be 1 is " + events.size(), events.size(), 1);
    }
