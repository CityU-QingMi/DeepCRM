    @Test
    public void testIsTraceEnabled() {
        final ListAppender appender = new ListAppender("List");
        appender.start();
        final Logger root = Logger.getRootLogger();
        try {
            root.getLogger().addAppender(appender);
            root.setLevel(Level.INFO);

            final Logger tracer = Logger.getLogger("com.example.Tracer");
            tracer.setLevel(Level.TRACE);

            assertTrue(tracer.isTraceEnabled());
            assertFalse(root.isTraceEnabled());
            appender.stop();
        } finally {
            root.getLogger().removeAppender(appender);
        }
    }
