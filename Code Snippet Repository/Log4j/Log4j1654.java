    @Test
    public void debugChangeLevelsMap() {
        logger.debug("Debug message 1");
        final List<LogEvent> events = app.getEvents();
        assertEventCount(events, 1);
        final Map<String, Level> map = new HashMap<>();
        map.put(logger.getName(), Level.OFF);
        Configurator.setLevel(map);
        logger.debug("Debug message 2");
        assertEventCount(events, 1);
        map.put(logger.getName(), Level.DEBUG);
        Configurator.setLevel(map);
        logger.debug("Debug message 3");
        assertEventCount(events, 2);
    }
