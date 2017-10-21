    @Test
    public void testFilter() {
        StructuredDataMessage msg = new StructuredDataMessage("Test", "This is a test", "Service");
        msg.put("Key1", "Value2");
        msg.put("Key2", "Value1");
        final Logger logger = LogManager.getLogger("org.apache.logging.log4j.core.Logging");
        logger.debug(msg);
        msg = new StructuredDataMessage("Test", "This is a test", "Service");
        msg.put("Key1", "Value1");
        msg.put("Key2", "Value2");
        logger.trace(msg);

        final List<LogEvent> list = app.getEvents();
        assertTrue("Events were generated", list == null || list.isEmpty());
    }
