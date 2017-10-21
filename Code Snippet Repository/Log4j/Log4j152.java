    @Override
    public L getLogger(final String name) {
        final LoggerContext context = getContext();
        final ConcurrentMap<String, L> loggers = getLoggersInContext(context);
        final L logger = loggers.get(name);
        if (logger != null) {
            return logger;
        }
        loggers.putIfAbsent(name, newLogger(name, context));
        return loggers.get(name);
    }
