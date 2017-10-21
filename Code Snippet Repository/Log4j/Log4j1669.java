    @Test
    public void testNoLocation() throws Exception {
        final Logger logger = LogManager.getLogger(AsyncAppender.class);
        logger.error("This is a test");
        logger.warn("Hello world!");
        Thread.sleep(100);
        System.out.println("app = " + app == null ? "null" : app);
        final List<String> list = app.getMessages();
        assertNotNull("No events generated", list);
        assertEquals("Incorrect number of events. Expected 2, got " + list.size(), list.size(), 2);
        String msg = list.get(0);
        String expected = "?  This is a test";
        assertEquals("Expected " + expected + ", Actual " + msg, expected, msg);
        msg = list.get(1);
        expected = "?  Hello world!";
        assertEquals("Expected " + expected + ", Actual " + msg, expected, msg);
    }
