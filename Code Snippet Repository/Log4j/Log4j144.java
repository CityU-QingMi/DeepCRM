    @Override
    public ExtendedLogger getLogger(final String name, final MessageFactory messageFactory) {
        // Note: This is the only method where we add entries to the 'loggerRegistry' ivar.
        final ExtendedLogger extendedLogger = loggerRegistry.getLogger(name, messageFactory);
        if (extendedLogger != null) {
            AbstractLogger.checkMessageFactory(extendedLogger, messageFactory);
            return extendedLogger;
        }
        final SimpleLogger simpleLogger = new SimpleLogger(name, defaultLevel, showLogName, showShortName, showDateTime,
                showContextMap, dateTimeFormat, messageFactory, props, stream);
        loggerRegistry.putIfAbsent(name, messageFactory, simpleLogger);
        return loggerRegistry.getLogger(name, messageFactory);
    }
