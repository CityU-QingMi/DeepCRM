    private MarkerPatternSelector(final PatternMatch[] properties, final String defaultPattern,
                                 final boolean alwaysWriteExceptions, final boolean disableAnsi,
                                 final boolean noConsoleNoAnsi, final Configuration config) {
        final PatternParser parser = PatternLayout.createPatternParser(config);
        for (final PatternMatch property : properties) {
            try {
                final List<PatternFormatter> list = parser.parse(property.getPattern(), alwaysWriteExceptions,
                        disableAnsi, noConsoleNoAnsi);
                formatterMap.put(property.getKey(), list.toArray(new PatternFormatter[list.size()]));
                patternMap.put(property.getKey(), property.getPattern());
            } catch (final RuntimeException ex) {
                throw new IllegalArgumentException("Cannot parse pattern '" + property.getPattern() + "'", ex);
            }
        }
        try {
            final List<PatternFormatter> list = parser.parse(defaultPattern, alwaysWriteExceptions, disableAnsi,
                    noConsoleNoAnsi);
            defaultFormatters = list.toArray(new PatternFormatter[list.size()]);
            this.defaultPattern = defaultPattern;
        } catch (final RuntimeException ex) {
            throw new IllegalArgumentException("Cannot parse pattern '" + defaultPattern + "'", ex);
        }
    }
