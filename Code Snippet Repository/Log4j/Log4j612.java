    @Override
    public Logger getLogger(final String name, final MessageFactory messageFactory) {
        // Note: This is the only method where we add entries to the 'loggerRegistry' ivar.
        Logger logger = loggerRegistry.getLogger(name, messageFactory);
        if (logger != null) {
            AbstractLogger.checkMessageFactory(logger, messageFactory);
            return logger;
        }

        logger = newInstance(this, name, messageFactory);
        loggerRegistry.putIfAbsent(name, messageFactory, logger);
        return loggerRegistry.getLogger(name, messageFactory);
    }
