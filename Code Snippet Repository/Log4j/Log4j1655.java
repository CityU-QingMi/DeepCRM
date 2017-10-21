    @Test
    public void debugChangeLevelsMapChildLoggers() {
        logger.debug("Debug message 1");
        loggerChild.debug("Debug message 1 C");
        loggerGrandchild.debug("Debug message 1 GC");
        final List<LogEvent> events = app.getEvents();
        assertEventCount(events, 3);
        final Map<String, Level> map = new HashMap<>();
        map.put(logger.getName(), Level.OFF);
        map.put(loggerChild.getName(), Level.DEBUG);
        map.put(loggerGrandchild.getName(), Level.WARN);
        Configurator.setLevel(map);
        logger.debug("Debug message 2");
        loggerChild.debug("Debug message 2 C");
        loggerGrandchild.debug("Debug message 2 GC");
        assertEventCount(events, 4);
        map.put(logger.getName(), Level.DEBUG);
        map.put(loggerChild.getName(), Level.OFF);
        map.put(loggerGrandchild.getName(), Level.DEBUG);
        Configurator.setLevel(map);
        logger.debug("Debug message 3");
        loggerChild.debug("Debug message 3 C");
        loggerGrandchild.debug("Debug message 3 GC");
        assertEventCount(events, 6);
    }
