    @Test
    public void testException() {
        final Logger logger = context.getLogger("LoggerTest");
        final Throwable cause = new NullPointerException("null pointer");
        final Throwable parent = new IllegalArgumentException("IllegalArgument", cause);
        logger.error("Exception", parent);
        final List<String> msgs = app.getMessages();
        assertNotNull(msgs);
        assertEquals("Incorrect number of messages. Should be 1 is " + msgs.size(), 1, msgs.size());
        assertTrue("No suppressed lines", msgs.get(0).contains("suppressed"));
    }
