    @Test
    public void testMDC() throws Exception {
        MDC.put("Key1", "John");
        MDC.put("Key2", "Smith");
        final Logger logger = Logger.getLogger("org.apache.test.logging");
        logger.debug("This is a test");
        final ListAppender listApp = (ListAppender) CTX.getAppender("List");
        assertNotNull(listApp);
        final List<String> msgs = listApp.getMessages();
        assertNotNull("No messages received", msgs);
        assertTrue(msgs.size() == 1);
        assertTrue("Key1 is missing", msgs.get(0).contains("Key1=John"));
        assertTrue("Key2 is missing", msgs.get(0).contains("Key2=Smith"));
    }
