    @Test
    public void testLoggingWorks() throws Exception {
        final Logger logger = LogManager.getLogger();
        logger.error("This is a test");
        logger.warn("Hello world!");
        Thread.sleep(100);
        final List<String> list = context.getListAppender("List").getMessages();
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events. Expected 2, got " + list.size(), list.size() == 2);
        String msg = list.get(0);
        String expected = getClass().getName() + " This is a test";
        assertTrue("Expected " + expected + ", Actual " + msg, expected.equals(msg));
        msg = list.get(1);
        expected = getClass().getName() + " Hello world!";
        assertTrue("Expected " + expected + ", Actual " + msg, expected.equals(msg));
    }
