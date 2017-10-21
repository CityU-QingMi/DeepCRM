        @Override
        public ColumnConfig build() {
            if (Strings.isEmpty(name)) {
                LOGGER.error("The column config is not valid because it does not contain a column name.");
                return null;
            }

            final boolean isPattern = Strings.isNotEmpty(pattern);
            final boolean isLiteralValue = Strings.isNotEmpty(literal);

            if ((isPattern && isLiteralValue) || (isPattern && isEventTimestamp) || (isLiteralValue && isEventTimestamp)) {
                LOGGER.error("The pattern, literal, and isEventTimestamp attributes are mutually exclusive.");
                return null;
            }

            if (isEventTimestamp) {
                return new ColumnConfig(name, null, null, true, false, false);
            }

            if (isLiteralValue) {
                return new ColumnConfig(name, null, literal, false, false, false);
            }

            if (isPattern) {
                final PatternLayout layout =
                    PatternLayout.newBuilder()
                        .withPattern(pattern)
                        .withConfiguration(configuration)
                        .withAlwaysWriteExceptions(false)
                        .build();
                return new ColumnConfig(name, layout, null, false, isUnicode, isClob);
            }

            LOGGER.error("To configure a column you must specify a pattern or literal or set isEventDate to true.");
            return null;
        }
