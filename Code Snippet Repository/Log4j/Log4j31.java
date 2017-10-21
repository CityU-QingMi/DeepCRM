    @Test
    public void testTraceWithException() {
        final ListAppender appender = new ListAppender("List");
        appender.start();
        final Logger root = Logger.getRootLogger();
        try {
            root.getLogger().addAppender(appender);
            root.setLevel(Level.INFO);

            final Logger tracer = Logger.getLogger("com.example.Tracer");
            tracer.setLevel(Level.TRACE);
            final NullPointerException ex = new NullPointerException();

            tracer.trace("Message 1", ex);
            root.trace("Discarded Message", ex);
            root.trace("Discarded Message", ex);

            final List<LogEvent> msgs = appender.getEvents();
            assertEquals(1, msgs.size());
            final LogEvent event = msgs.get(0);
            assertEquals(org.apache.logging.log4j.Level.TRACE, event.getLevel());
            assertEquals("Message 1", event.getMessage().getFormattedMessage());
            appender.stop();
        } finally {
            root.getLogger().removeAppender(appender);
        }
    }
