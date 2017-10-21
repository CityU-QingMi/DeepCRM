    @Test
    public void testMethodLogger() throws Exception {
        final ListAppender app = ctx.getListAppender("Method").clear();
        final Logger logger = Logger.getLogger("MethodLogger");
        logger.info("More messages.");
        logger.warning("CATASTROPHE INCOMING!");
        logger.severe("ZOMBIES!!!");
        logger.warning("brains~~~");
        logger.info("Itchy. Tasty.");
        final List<String> messages = app.getMessages();
        assertEquals("Incorrect number of messages.", 5, messages.size());
        for (final String message : messages) {
            assertEquals("Incorrect caller method name.", "testMethodLogger", message);
        }
    }
