    @Test
    public void logger2() {
        logger2.traceEntry();
        logger2.debug("debug message");
        logger2.error("Test Message");
        logger2.info("Info Message");
        logger2.warn("warn Message");
        logger2.traceExit();
        List<LogEvent> events = app1.getEvents();
        assertEquals("Incorrect number of events. Expected 2, actual " + events.size(), events.size(), 2);
        events = app2.getEvents();
        assertEquals("Incorrect number of events. Expected 4, actual " + events.size(), events.size(), 4);
    }
