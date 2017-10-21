    @Test
    public void debugChangeLevelAllChildrenLoggers() {
        // Use logger AND child loggers
        logger.debug("Debug message 1");
        loggerChild.debug("Debug message 1 child");
        loggerGrandchild.debug("Debug message 1 grandchild");
        final List<LogEvent> events = app.getEvents();
        assertEventCount(events, 3);
        Configurator.setAllLevels(logger.getName(), Level.OFF);
        logger.debug("Debug message 2");
        loggerChild.warn("Warn message 2 child");
        loggerGrandchild.fatal("Fatal message 2 grandchild");
        assertEventCount(events, 3);
        Configurator.setAllLevels(logger.getName(), Level.DEBUG);
        logger.debug("Debug message 3");
        loggerChild.warn("Trace message 3 child");
        loggerGrandchild.trace("Fatal message 3 grandchild");
        assertEventCount(events, 5);
    }
