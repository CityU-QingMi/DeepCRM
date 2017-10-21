    @Test
    public void debugChangeLevel() {
        logger.debug("Debug message 1");
        final List<LogEvent> events = app.getEvents();
        assertEventCount(events, 1);
        Configurator.setLevel(logger.getName(), Level.OFF);
        logger.debug("Debug message 2");
        assertEventCount(events, 1);
        Configurator.setLevel(logger.getName(), Level.DEBUG);
        logger.debug("Debug message 3");
        assertEventCount(events, 2);
    }
