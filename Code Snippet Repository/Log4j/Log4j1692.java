    @Test
    public void testRecovery() throws Exception {
        onceLogger.error("Fail once");
        onceLogger.error("Fail again");
        List<LogEvent> events = app.getEvents();
        assertNotNull(events);
        assertEquals("Incorrect number of events. Should be 2 is " + events.size(), events.size(), 2);
        app.clear();
        Thread.sleep(1100);
        onceLogger.error("Fail after recovery interval");
        onceLogger.error("Second log message");
        events = app.getEvents();
        assertEquals("Did not recover", events.size(), 0);
        events = foApp.getEvents();
        assertEquals("Incorrect number of events in primary appender", events.size(), 2);
    }
