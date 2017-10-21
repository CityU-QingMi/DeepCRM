    @Test
    public void logger1() {
        logger1.traceEntry();
        logger1.debug("debug message");
        logger1.error("Test Message");
        logger1.info("Info Message");
        logger1.warn("warn Message");
        logger1.traceExit();
        List<LogEvent> events = app1.getEvents();
        assertEquals("Incorrect number of events. Expected 6, actual " + events.size(), 6, events.size());
        events = app2.getEvents();
        assertEquals("Incorrect number of events. Expected 1, actual " + events.size(), 1, events.size());
    }
