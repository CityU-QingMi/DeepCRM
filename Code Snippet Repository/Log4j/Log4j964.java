    @Deprecated
    public static LoggerConfig createLogger(final String additivity,
            // @formatter:off
            final Level level,
            @PluginAttribute("name") final String loggerName,
            final String includeLocation,
            final AppenderRef[] refs,
            final Property[] properties,
            @PluginConfiguration final Configuration config,
            final Filter filter) {
            // @formatter:on
        if (loggerName == null) {
            LOGGER.error("Loggers cannot be configured without a name");
            return null;
        }

        final List<AppenderRef> appenderRefs = Arrays.asList(refs);
        final String name = loggerName.equals(ROOT) ? Strings.EMPTY : loggerName;
        final boolean additive = Booleans.parseBoolean(additivity, true);

        return new LoggerConfig(name, appenderRefs, filter, level, additive, properties, config,
                includeLocation(includeLocation));
    }
