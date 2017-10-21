    @Test
    public void testJavaScriptPatternSelector() throws Exception {
        final org.apache.logging.log4j.Logger logger = LogManager.getLogger("TestJavaScriptPatternSelector");
        final org.apache.logging.log4j.Logger logger2 = LogManager.getLogger("JavascriptNoLocation");
        logger.traceEntry();
        logger.info("Hello World");
        logger2.info("No location information");
        logger.traceExit();
        final ListAppender app = (ListAppender) context.getRequiredAppender("List3");
        assertNotNull("No ListAppender", app);
        final List<String> messages = app.getMessages();
        assertNotNull("No Messages", messages);
        assertTrue("Incorrect number of messages. Expected 4, Actual " + messages.size() + ": " + messages, messages.size() == 4);
        String expect = "[TRACE] TestJavaScriptPatternSelector ====== " +
                "o.a.l.l.c.PatternSelectorTest.testJavaScriptPatternSelector:85 Enter ======" + Strings.LINE_SEPARATOR;
        assertEquals(expect, messages.get(0));
        expect = "[INFO ] TestJavaScriptPatternSelector " +
                "o.a.l.l.c.PatternSelectorTest.testJavaScriptPatternSelector.86 Hello World" + Strings.LINE_SEPARATOR;
        assertEquals(expect, messages.get(1));
        assertEquals("[INFO ] JavascriptNoLocation No location information" + Strings.LINE_SEPARATOR, messages.get(2));
        app.clear();
    }
