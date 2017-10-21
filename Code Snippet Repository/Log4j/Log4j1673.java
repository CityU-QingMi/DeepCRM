    @Test
    public void rewriteTest() throws Exception {
        final Logger logger = LogManager.getLogger(AsyncAppender.class);
        logger.error("This is a test");
        logger.warn("Hello world!");
        final long timeoutMillis = TIMEOUT_MILLIS;
        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        final List<String> list = listAppender.getMessages(2, timeoutMillis, timeUnit);
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events after " + timeoutMillis + " " + timeUnit + ". Expected 2, got "
                + list.size(), list.size() == 2);
        String msg = list.get(0);
        String expected = AsyncAppenderTest.class.getName() + " rewriteTest This is a test";
        assertTrue("Expected " + expected + ", Actual " + msg, expected.equals(msg));
        msg = list.get(1);
        expected = AsyncAppenderTest.class.getName() + " rewriteTest Hello world!";
        assertTrue("Expected " + expected + ", Actual " + msg, expected.equals(msg));
    }
