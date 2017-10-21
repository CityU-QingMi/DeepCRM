    @Deprecated
    public static Serializer createSerializer(final Configuration configuration, final RegexReplacement replace,
            final String pattern, final String defaultPattern, final PatternSelector patternSelector,
            final boolean alwaysWriteExceptions, final boolean noConsoleNoAnsi) {
        final SerializerBuilder builder = newSerializerBuilder();
        builder.setAlwaysWriteExceptions(alwaysWriteExceptions);
        builder.setConfiguration(configuration);
        builder.setDefaultPattern(defaultPattern);
        builder.setNoConsoleNoAnsi(noConsoleNoAnsi);
        builder.setPattern(pattern);
        builder.setPatternSelector(patternSelector);
        builder.setReplace(replace);
        return builder.build();
    }
