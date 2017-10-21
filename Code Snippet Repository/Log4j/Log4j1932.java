    @Test
    public void routingTest() {
        StructuredDataMessage msg = new StructuredDataMessage("Test", "This is a test", "Service");
        EventLogger.logEvent(msg);
        final List<LogEvent> list = app.getEvents();
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events. Expected 1, got " + list.size(), list.size() == 1);
        msg = new StructuredDataMessage("Test", "This is a test", "Alert");
        EventLogger.logEvent(msg);
        File file = new File(ALERT_LOG_FILE);
        assertTrue("Alert file was not created", file.exists());
        msg = new StructuredDataMessage("Test", "This is a test", "Activity");
        EventLogger.logEvent(msg);
        file = new File(ACTIVITY_LOG_FILE);
        assertTrue("Activity file was not created", file.exists());
    }
