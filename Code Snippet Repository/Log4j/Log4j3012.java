    @Test
    public void testMethodLogger() throws Exception {
        final SLF4JLogger logger = (SLF4JLogger) LogManager.getLogger("MethodLogger");
        final StringListAppender<ILoggingEvent> app = TestUtil.getListAppender(logger, "Method");
        logger.info("More messages.");
        logger.warn("CATASTROPHE INCOMING!");
        logger.error("ZOMBIES!!!");
        logger.warn("brains~~~");
        logger.info("Itchy. Tasty.");
        final List<String> messages = app.strList;
        assertEquals("Incorrect number of messages.", 5, messages.size());
        for (final String message : messages) {
            assertEquals("Incorrect caller method name.", "testMethodLogger", message);
        }
    }
