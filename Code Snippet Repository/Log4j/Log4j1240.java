        @Override
        public MarkerPatternSelector build() {
            if (defaultPattern == null) {
                defaultPattern = PatternLayout.DEFAULT_CONVERSION_PATTERN;
            }
            if (properties == null || properties.length == 0) {
                LOGGER.warn("No marker patterns were provided with PatternMatch");
                return null;
            }
            return new MarkerPatternSelector(properties, defaultPattern, alwaysWriteExceptions, disableAnsi,
                    noConsoleNoAnsi, configuration);
        }
