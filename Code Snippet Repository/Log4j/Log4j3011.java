    @Test
    public void testClassLogger() throws Exception {
        final SLF4JLogger logger = (SLF4JLogger) LogManager.getLogger("ClassLogger");
        final StringListAppender<ILoggingEvent> app = TestUtil.getListAppender(logger, "Class");
        logger.info("Ignored message contents.");
        logger.warn("Verifying the caller class is still correct.");
        logger.error("Hopefully nobody breaks me!");
        final List<String> messages = app.strList;
        assertEquals("Incorrect number of messages.", 3, messages.size());
        for (final String message : messages) {
            assertEquals("Incorrect caller class name.", this.getClass().getName(), message);
        }
    }
