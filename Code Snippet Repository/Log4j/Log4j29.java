    @Test
    public void testMDC() throws Exception {
        MDC.put("Type", "Service");
        MDC.put("Name", "John Smith");
        final Logger logger = Logger.getLogger("org.apache.test.logging");
        logger.debug("This is a test");
        final ListAppender listApp = (ListAppender) CTX.getAppender("List");
        assertNotNull(listApp);
        final List<String> msgs = listApp.getMessages();
        assertNotNull("No messages received", msgs);
        assertTrue(msgs.size() == 1);
        assertTrue("Type is missing", msgs.get(0).contains("Type=Service"));
        assertTrue("Name is missing", msgs.get(0).contains("Name=John Smith"));
    }
