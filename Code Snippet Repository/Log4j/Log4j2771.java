    @Test
    public void testLog() throws Exception {
        logger.info("Informative message here.");
        final List<LogEvent> events = eventAppender.getEvents();
        assertThat(events, hasSize(1));
        final LogEvent event = events.get(0);
        assertThat(event, instanceOf(Log4jLogEvent.class));
        assertEquals(Level.INFO, event.getLevel());
        assertEquals(LOGGER_NAME, event.getLoggerName());
        assertEquals("Informative message here.", event.getMessage().getFormattedMessage());
        assertEquals(ApiLogger.class.getName(), event.getLoggerFqcn());
    }
