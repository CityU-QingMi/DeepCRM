    @PluginFactory
    public static LoggerConfig createLogger(
         // @formatter:off
        @PluginAttribute(value = "additivity", defaultBoolean = true) final boolean additivity,
        @PluginAttribute("level") final Level level,
        @Required(message = "Loggers cannot be configured without a name") @PluginAttribute("name") final String loggerName,
        @PluginAttribute("includeLocation") final String includeLocation,
        @PluginElement("AppenderRef") final AppenderRef[] refs,
        @PluginElement("Properties") final Property[] properties,
        @PluginConfiguration final Configuration config,
        @PluginElement("Filter") final Filter filter
        // @formatter:on
    ) {
        final String name = loggerName.equals(ROOT) ? Strings.EMPTY : loggerName;
        return new LoggerConfig(name, Arrays.asList(refs), filter, level, additivity, properties, config,
            includeLocation(includeLocation));
    }
