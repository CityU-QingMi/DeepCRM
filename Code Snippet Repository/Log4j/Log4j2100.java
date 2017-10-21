    @Test
    public void testGroovyFilter() throws Exception {
        final Logger logger = LogManager.getLogger("TestGroovyFilter");
        logger.traceEntry();
        logger.info("This should not be logged");
        ThreadContext.put("UserId", "JohnDoe");
        logger.info("This should be logged");
        ThreadContext.clearMap();
        final ListAppender app = getContext().getListAppender("List");
        try {
            final List<String> messages = app.getMessages();
            assertNotNull("No Messages", messages);
            assertTrue("Incorrect number of messages. Expected 2, Actual " + messages.size(), messages.size() == 2);
        } finally {
            app.clear();
        }
    }
