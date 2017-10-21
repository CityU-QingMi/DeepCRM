    @Test
    @SuppressWarnings("")
    public void testForcedLog() {
        final MockCategory category = new MockCategory("org.example.foo");
        category.setAdditivity(false);
        category.getLogger().addAppender(appender);
        category.info("Hello, World");
        final List<LogEvent> list = appender.getEvents();
        int events = list.size();
        assertTrue("Number of events should be 1, was " + events, events == 1);
        LogEvent event = list.get(0);
        Message msg = event.getMessage();
        assertNotNull("No message", msg);
        assertTrue("Incorrect Message type", msg instanceof ObjectMessage);
        Object[] objects = msg.getParameters();
        assertTrue("Incorrect Object type", objects[0] instanceof String);
        appender.clear();
        category.log(Priority.INFO, "Hello, World");
        events = list.size();
        assertTrue("Number of events should be 1, was " + events, events == 1);
        event = list.get(0);
        msg = event.getMessage();
        assertNotNull("No message", msg);
        assertTrue("Incorrect Message type", msg instanceof ObjectMessage);
        objects = msg.getParameters();
        assertTrue("Incorrect Object type", objects[0] instanceof String);
        appender.clear();
    }
