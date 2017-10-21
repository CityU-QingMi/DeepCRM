    @Test
    public void paramWithExceptionTest() throws Exception {
        logger.error("Throwing with parameters {}", "TestParam", new NullPointerException("Test Exception"));
        final List<LogEvent> events = app.getEvents();
        assertNotNull("Log event list not returned", events);
        assertTrue("Incorrect number of log events: expected 1, actual " + events.size(), events.size() == 1);
        final LogEvent event = events.get(0);
        final Throwable thrown = event.getThrown();
        assertNotNull("No throwable present in log event", thrown);
        final Message msg = event.getMessage();
        assertTrue("Incorrect message type. Expected ParameterizedMessage/ReusableParameterizedMessage, actual " + msg.getClass().getSimpleName(),
                msg instanceof ParameterizedMessage || msg instanceof ReusableParameterizedMessage);

    }
