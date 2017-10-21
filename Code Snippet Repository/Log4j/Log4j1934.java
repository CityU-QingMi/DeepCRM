    @Test(timeout = 5000)
    public void routingTest() throws InterruptedException {
        StructuredDataMessage msg = new StructuredDataMessage("1", "This is a test 1", "Service");
        EventLogger.logEvent(msg);
        final List<LogEvent> list = app.getEvents();
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events. Expected 1, got " + list.size(), list.size() == 1);
        msg = new StructuredDataMessage("2", "This is a test 2", "Service");
        EventLogger.logEvent(msg);
        msg = new StructuredDataMessage("3", "This is a test 3", "Service");
        EventLogger.logEvent(msg);
        final String[] files = {IDLE_LOG_FILE1, IDLE_LOG_FILE2, IDLE_LOG_FILE3, MANUAL_LOG_FILE1, MANUAL_LOG_FILE2, MANUAL_LOG_FILE3};
        assertFileExistance(files);

        assertEquals("Incorrect number of appenders with IdlePurgePolicy.", 3, routingAppenderIdle.getAppenders().size());
        assertEquals("Incorrect number of appenders with IdlePurgePolicy with HangingAppender.",
                3, routingAppenderIdleWithHangingAppender.getAppenders().size());
        assertEquals("Incorrect number of appenders manual purge.", 3, routingAppenderManual.getAppenders().size());

        Thread.sleep(3000);
        EventLogger.logEvent(msg);

        assertEquals("Incorrect number of appenders with IdlePurgePolicy.", 1, routingAppenderIdle.getAppenders().size());
        assertEquals("Incorrect number of appenders with manual purge.", 3, routingAppenderManual.getAppenders().size());

        routingAppenderManual.deleteAppender("1");
        routingAppenderManual.deleteAppender("2");
        routingAppenderManual.deleteAppender("3");

        assertEquals("Incorrect number of appenders with IdlePurgePolicy.", 1, routingAppenderIdle.getAppenders().size());
        assertEquals("Incorrect number of appenders with manual purge.", 0, routingAppenderManual.getAppenders().size());

        msg = new StructuredDataMessage("5", "This is a test 5", "Service");
        EventLogger.logEvent(msg);

        assertEquals("Incorrect number of appenders with manual purge.", 1, routingAppenderManual.getAppenders().size());

        routingAppenderManual.deleteAppender("5");
        routingAppenderManual.deleteAppender("5");

        assertEquals("Incorrect number of appenders with manual purge.", 0, routingAppenderManual.getAppenders().size());
    }
