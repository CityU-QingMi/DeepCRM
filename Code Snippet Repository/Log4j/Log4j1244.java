    private PatternLayout(final Configuration config, final RegexReplacement replace, final String eventPattern,
            final PatternSelector patternSelector, final Charset charset, final boolean alwaysWriteExceptions,
            final boolean disableAnsi, final boolean noConsoleNoAnsi, final String headerPattern,
            final String footerPattern) {
        super(config, charset,
                newSerializerBuilder()
                        .setConfiguration(config)
                        .setReplace(replace)
                        .setPatternSelector(patternSelector)
                        .setAlwaysWriteExceptions(alwaysWriteExceptions)
                        .setDisableAnsi(disableAnsi)
                        .setNoConsoleNoAnsi(noConsoleNoAnsi)
                        .setPattern(headerPattern)
                        .build(),
                newSerializerBuilder()
                        .setConfiguration(config)
                        .setReplace(replace)
                        .setPatternSelector(patternSelector)
                        .setAlwaysWriteExceptions(alwaysWriteExceptions)
                        .setDisableAnsi(disableAnsi)
                        .setNoConsoleNoAnsi(noConsoleNoAnsi)
                        .setPattern(footerPattern)
                        .build());
        this.conversionPattern = eventPattern;
        this.patternSelector = patternSelector;
        this.eventSerializer = newSerializerBuilder()
                .setConfiguration(config)
                .setReplace(replace)
                .setPatternSelector(patternSelector)
                .setAlwaysWriteExceptions(alwaysWriteExceptions)
                .setDisableAnsi(disableAnsi)
                .setNoConsoleNoAnsi(noConsoleNoAnsi)
                .setPattern(eventPattern)
                .setDefaultPattern(DEFAULT_CONVERSION_PATTERN)
                .build();
    }
