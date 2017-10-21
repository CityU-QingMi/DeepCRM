    protected AsyncLoggerConfig(final String name,
            final List<AppenderRef> appenders, final Filter filter,
            final Level level, final boolean additive,
            final Property[] properties, final Configuration config,
            final boolean includeLocation) {
        super(name, appenders, filter, level, additive, properties, config,
                includeLocation);
        delegate = config.getAsyncLoggerConfigDelegate();
        delegate.setLogEventFactory(getLogEventFactory());
    }
