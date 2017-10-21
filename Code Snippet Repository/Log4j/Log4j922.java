    @Override
    public synchronized void addLoggerAppender(final org.apache.logging.log4j.core.Logger logger,
            final Appender appender) {
        final String loggerName = logger.getName();
        appenders.putIfAbsent(appender.getName(), appender);
        final LoggerConfig lc = getLoggerConfig(loggerName);
        if (lc.getName().equals(loggerName)) {
            lc.addAppender(appender, null, null);
        } else {
            final LoggerConfig nlc = new LoggerConfig(loggerName, lc.getLevel(), lc.isAdditive());
            nlc.addAppender(appender, null, null);
            nlc.setParent(lc);
            loggerConfigs.putIfAbsent(loggerName, nlc);
            setParents();
            logger.getContext().updateLoggers();
        }
    }
