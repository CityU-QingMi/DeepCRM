    @Test
    public void testMarkerPatternSelector() throws Exception {
        final org.apache.logging.log4j.Logger logger = LogManager.getLogger("TestMarkerPatternSelector");
        logger.traceEntry();
        logger.info("Hello World");
        logger.traceExit();
        final ListAppender app = (ListAppender) context.getRequiredAppender("List");
        assertNotNull("No ListAppender", app);
        final List<String> messages = app.getMessages();
        assertNotNull("No Messages", messages);
        assertTrue("Incorrect number of messages. Expected 3, Actual " + messages.size() + ": " + messages, messages.size() == 3);
        final String expect = String.format("[TRACE] TestMarkerPatternSelector ====== "
                + "o.a.l.l.c.PatternSelectorTest.testMarkerPatternSelector:43 Enter ======%n");
        assertEquals(expect, messages.get(0));
        assertEquals("[INFO ] TestMarkerPatternSelector Hello World" + Strings.LINE_SEPARATOR, messages.get(1));
        app.clear();
    }
