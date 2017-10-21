    private void buildRootLogger(final String rootLoggerValue) {
        if (rootLoggerValue == null) {
            return;
        }
        final String[] rootLoggerParts = rootLoggerValue.split(COMMA_DELIMITED_RE);
        final String rootLoggerLevel = getLevelString(rootLoggerParts, Level.ERROR.name());
        final RootLoggerComponentBuilder loggerBuilder = builder.newRootLogger(rootLoggerLevel);
        //
        final String[] sortedAppenderNames = Arrays.copyOfRange(rootLoggerParts, 1, rootLoggerParts.length);
        Arrays.sort(sortedAppenderNames);
        for (final String appender : sortedAppenderNames) {
            loggerBuilder.add(builder.newAppenderRef(appender));
        }
        builder.add(loggerBuilder);
    }
