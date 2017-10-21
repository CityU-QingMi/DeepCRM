    @Test
    public void testReplacement() {
        logger.error(this.getClass().getName());
        List<String> msgs = app.getMessages();
        assertNotNull(msgs);
        assertEquals("Incorrect number of messages. Should be 1 is " + msgs.size(), 1, msgs.size());
        assertTrue("Replacement failed - expected ending " + EXPECTED + " Actual " + msgs.get(0),
            msgs.get(0).endsWith(EXPECTED));
        app.clear();
        ThreadContext.put("MyKey", "Apache");
        logger.error("This is a test for ${ctx:MyKey}");
        msgs = app.getMessages();
        assertNotNull(msgs);
        assertEquals("Incorrect number of messages. Should be 1 is " + msgs.size(), 1, msgs.size());
        assertEquals("LoggerTest This is a test for Apache" + Strings.LINE_SEPARATOR, msgs.get(0));
    }
