    @Deprecated
    public static ColumnConfig createColumnConfig(final Configuration config, final String name, final String pattern,
                                                  final String literalValue, final String eventTimestamp,
                                                  final String unicode, final String clob) {
        if (Strings.isEmpty(name)) {
            LOGGER.error("The column config is not valid because it does not contain a column name.");
            return null;
        }

        final boolean isEventTimestamp = Boolean.parseBoolean(eventTimestamp);
        final boolean isUnicode = Booleans.parseBoolean(unicode, true);
        final boolean isClob = Boolean.parseBoolean(clob);

        return newBuilder()
            .setConfiguration(config)
            .setName(name)
            .setPattern(pattern)
            .setLiteral(literalValue)
            .setEventTimestamp(isEventTimestamp)
            .setUnicode(isUnicode)
            .setClob(isClob)
            .build();
    }
