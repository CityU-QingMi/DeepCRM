    @Override
    public ExtendedLogger getLogger(final String name) {
        final ExtendedLogger extendedLogger = map.get(name);
        if (extendedLogger != null) {
			return extendedLogger;
        }
        final ExtendedLogger logger = new TestLogger(name);
        map.put(name, logger);
        return logger;
    }
