    @Test
    public void debugChangeRootLevel() {
        logger.debug("Debug message 1");
        final List<LogEvent> events = app.getEvents();
        assertEventCount(events, 1);
        Configurator.setRootLevel(Level.OFF);
        logger.debug("Debug message 2");
        assertEventCount(events, 1);
        Configurator.setRootLevel(Level.DEBUG);
        logger.debug("Debug message 3");
        assertEventCount(events, 2);
    }
