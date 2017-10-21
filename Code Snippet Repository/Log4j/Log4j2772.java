    private void testMessage(final String string) {
        final Logger root = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        root.info("Test info " + string);
        root.config("Test info " + string);
        root.fine("Test info " + string);
        final List<LogEvent> events = eventAppender.getEvents();
        assertThat(events, hasSize(3));
        for (final LogEvent event : events) {
            final String message = event.getMessage().getFormattedMessage();
            assertThat(message, equalTo("Test info " + string));
        }
    }
