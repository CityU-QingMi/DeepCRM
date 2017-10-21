    @Test
    public void testException() throws Exception {
        final Logger logger = LogManager.getLogger(AsyncAppender.class);
        final Exception parent = new IllegalStateException("Test");
        final Throwable child = new LoggingException("This is a test", parent);
        logger.error("This is a test", child);
        final long timeoutMillis = TIMEOUT_MILLIS;
        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        final List<String> list = listAppender.getMessages(1, timeoutMillis, timeUnit);
        assertNotNull("No events generated", list);
        assertTrue("Incorrect number of events after " + timeoutMillis + " " + timeUnit + ". Expected 1, got "
                + list.size(), list.size() == 1);
        final String msg = list.get(0);
        assertTrue("No parent exception", msg.contains("java.lang.IllegalStateException"));
    }
