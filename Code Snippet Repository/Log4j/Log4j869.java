        @PluginFactory
        public static LoggerConfig createLogger(
                @PluginAttribute("additivity") final String additivity,
                @PluginAttribute("level") final String levelName,
                @PluginAttribute("includeLocation") final String includeLocation,
                @PluginElement("AppenderRef") final AppenderRef[] refs,
                @PluginElement("Properties") final Property[] properties,
                @PluginConfiguration final Configuration config,
                @PluginElement("Filter") final Filter filter) {
            final List<AppenderRef> appenderRefs = Arrays.asList(refs);
            Level level;
            try {
                level = Level.toLevel(levelName, Level.ERROR);
            } catch (final Exception ex) {
                LOGGER.error(
                        "Invalid Log level specified: {}. Defaulting to Error",
                        levelName);
                level = Level.ERROR;
            }
            final boolean additive = Booleans.parseBoolean(additivity, true);

            return new AsyncLoggerConfig(LogManager.ROOT_LOGGER_NAME,
                    appenderRefs, filter, level, additive, properties, config,
                    AsyncLoggerConfig.includeLocation(includeLocation));
        }
