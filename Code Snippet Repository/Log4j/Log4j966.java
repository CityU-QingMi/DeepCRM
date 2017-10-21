        @PluginFactory
        public static LoggerConfig createLogger(
                // @formatter:off
                @PluginAttribute("additivity") final String additivity,
                @PluginAttribute("level") final Level level,
                @PluginAttribute("includeLocation") final String includeLocation,
                @PluginElement("AppenderRef") final AppenderRef[] refs,
                @PluginElement("Properties") final Property[] properties,
                @PluginConfiguration final Configuration config,
                @PluginElement("Filter") final Filter filter) {
                // @formatter:on
            final List<AppenderRef> appenderRefs = Arrays.asList(refs);
            final Level actualLevel = level == null ? Level.ERROR : level;
            final boolean additive = Booleans.parseBoolean(additivity, true);

            return new LoggerConfig(LogManager.ROOT_LOGGER_NAME, appenderRefs, filter, actualLevel, additive,
                    properties, config, includeLocation(includeLocation));
        }
