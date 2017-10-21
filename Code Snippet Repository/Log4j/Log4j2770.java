    @Test
    public void testGlobalLogger() throws Exception {
        final Logger root = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        root.info("Test info message");
        root.config("Test info message");
        root.fine("Test info message");
        final List<LogEvent> events = eventAppender.getEvents();
        assertThat(events, hasSize(3));
        for (final LogEvent event : events) {
            final String message = event.getMessage().getFormattedMessage();
            assertThat(message, equalTo("Test info message"));
        }
    }
