    @Test
    public void testJavascriptFilter() throws Exception {
        final Logger logger = LogManager.getLogger("TestJavaScriptFilter");
        logger.traceEntry();
        logger.info("This should not be logged");
        ThreadContext.put("UserId", "JohnDoe");
        logger.info("This should be logged");
        ThreadContext.clearMap();
        final ListAppender app = getContext().getListAppender("List");
        final List<String> messages = app.getMessages();
        try {
            assertNotNull("No Messages", messages);
            assertTrue("Incorrect number of messages. Expected 2, Actual " + messages.size(), messages.size() == 2);
        } finally {
            app.clear();
        }
    }
