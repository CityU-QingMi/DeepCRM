        @Override
        public Serializer build() {
            if (Strings.isEmpty(pattern) && Strings.isEmpty(defaultPattern)) {
                return null;
            }
            if (patternSelector == null) {
                try {
                    final PatternParser parser = createPatternParser(configuration);
                    final List<PatternFormatter> list = parser.parse(pattern == null ? defaultPattern : pattern,
                            alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi);
                    final PatternFormatter[] formatters = list.toArray(new PatternFormatter[0]);
                    return new PatternSerializer(formatters, replace);
                } catch (final RuntimeException ex) {
                    throw new IllegalArgumentException("Cannot parse pattern '" + pattern + "'", ex);
                }
            }
            return new PatternSelectorSerializer(patternSelector, replace);
        }
