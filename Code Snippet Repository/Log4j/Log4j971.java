    public static ReliabilityStrategy getReliabilityStrategy(final LoggerConfig loggerConfig) {

        final String strategy = PropertiesUtil.getProperties().getStringProperty("log4j.ReliabilityStrategy",
                "AwaitCompletion");
        if ("AwaitCompletion".equals(strategy)) {
            return new AwaitCompletionReliabilityStrategy(loggerConfig);
        }
        if ("AwaitUnconditionally".equals(strategy)) {
            return new AwaitUnconditionallyReliabilityStrategy(loggerConfig);
        }
        if ("Locking".equals(strategy)) {
            return new LockingReliabilityStrategy(loggerConfig);
        }
        try {
            final Class<? extends ReliabilityStrategy> cls = LoaderUtil.loadClass(strategy).asSubclass(
                ReliabilityStrategy.class);
            return cls.getConstructor(LoggerConfig.class).newInstance(loggerConfig);
        } catch (final Exception dynamicFailed) {
            StatusLogger.getLogger().warn(
                    "Could not create ReliabilityStrategy for '{}', using default AwaitCompletionReliabilityStrategy: {}", strategy, dynamicFailed);
            return new AwaitCompletionReliabilityStrategy(loggerConfig);
        }
    }
