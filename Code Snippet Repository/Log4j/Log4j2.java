    static Logger getInstance(final LoggerContext context, final String name, final PrivateAdapter factory) {
        final ConcurrentMap<String, Logger> loggers = getLoggersMap(context);
        Logger logger = loggers.get(name);
        if (logger != null) {
            return logger;
        }
        logger = factory.newLogger(name, context);
        final Logger prev = loggers.putIfAbsent(name, logger);
        return prev == null ? logger : prev;
    }
