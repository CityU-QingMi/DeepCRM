    @Test
    public void testLog() {
        final Logger logger = context.getLogger();
        final List<LogEvent> events = listAppender.getEvents();
        assertThat(events, hasSize(0));
        logger.debug("Hello, {}", "World");
        assertThat(events, hasSize(1));
        logger.log(diagLevel, "Hello DIAG");
        assertThat(events, hasSize(2));
        assertEquals(events.get(1).getLevel(), diagLevel);

    }
