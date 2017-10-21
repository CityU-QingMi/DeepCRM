    @Test
    public void testScriptPatternSelector() throws Exception {
        final org.apache.logging.log4j.Logger logger = LogManager.getLogger("TestScriptPatternSelector");
        final org.apache.logging.log4j.Logger logger2 = LogManager.getLogger("NoLocation");
        logger.traceEntry();
        logger.info("Hello World");
        logger2.info("No location information");
        logger.traceExit();
        final ListAppender app = (ListAppender) context.getRequiredAppender("List2");
        assertNotNull("No ListAppender", app);
        final List<String> messages = app.getMessages();
        assertNotNull("No Messages", messages);
        assertTrue("Incorrect number of messages. Expected 4, Actual " + messages.size() + ": " + messages, messages.size() == 4);
        String expect = "[TRACE] TestScriptPatternSelector ====== " +
                "o.a.l.l.c.PatternSelectorTest.testScriptPatternSelector:62 Enter ======" + Strings.LINE_SEPARATOR;
        assertEquals(expect, messages.get(0));
        expect = "[INFO ] TestScriptPatternSelector o.a.l.l.c.PatternSelectorTest.testScriptPatternSelector.63 " +
                "Hello World" + Strings.LINE_SEPARATOR;
        assertEquals(expect, messages.get(1));
        assertEquals("[INFO ] NoLocation No location information" + Strings.LINE_SEPARATOR, messages.get(2));
        app.clear();
    }
