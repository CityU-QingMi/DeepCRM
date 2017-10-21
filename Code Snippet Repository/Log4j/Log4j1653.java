    @Test
    public void debugChangeLevelsChildLoggers() {
        final org.apache.logging.log4j.Logger loggerChild = context.getLogger(logger.getName() + ".child");
        // Use logger AND loggerChild
        logger.debug("Debug message 1");
        loggerChild.debug("Debug message 1 child");
        final List<LogEvent> events = app.getEvents();
        assertEventCount(events, 2);
        Configurator.setLevel(logger.getName(), Level.ERROR);
        Configurator.setLevel(loggerChild.getName(), Level.DEBUG);
        logger.debug("Debug message 2");
        loggerChild.debug("Debug message 2 child");
        assertEventCount(events, 3);
        Configurator.setLevel(logger.getName(), Level.DEBUG);
        logger.debug("Debug message 3");
        loggerChild.debug("Debug message 3 child");
        assertEventCount(events, 5);
    }
