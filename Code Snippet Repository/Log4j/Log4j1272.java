    @Deprecated
    public static ScriptPatternSelector createSelector(
            final AbstractScript script,
            final PatternMatch[] properties,
            final String defaultPattern,
            final boolean alwaysWriteExceptions,
            final boolean noConsoleNoAnsi,
            final Configuration configuration) {
        final Builder builder = newBuilder();
        builder.setScript(script);
        builder.setProperties(properties);
        builder.setDefaultPattern(defaultPattern);
        builder.setAlwaysWriteExceptions(alwaysWriteExceptions);
        builder.setNoConsoleNoAnsi(noConsoleNoAnsi);
        builder.setConfiguration(configuration);
        return builder.build();
    }
