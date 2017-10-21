    @Override
    public ExtendedLogger getLogger(final String name, final MessageFactory messageFactory) {
        // FIXME according to LOG4J2-1180, the below line should be:
        // FIXME if (!loggerRegistry.hasLogger(name, messageFactory)) {
        if (!loggerRegistry.hasLogger(name)) {
            // FIXME: should be loggerRegistry.putIfAbsent(name, messageFactory,
            loggerRegistry.putIfAbsent(name, null,
                    new SLF4JLogger(name, messageFactory, LoggerFactory.getLogger(name)));
        }
        // FIXME should be return loggerRegistry.getLogger(name, messageFactory);
        return loggerRegistry.getLogger(name);

        // TODO applying the above fixes causes (log4j-to-slf4j) LoggerTest to fail
    }
