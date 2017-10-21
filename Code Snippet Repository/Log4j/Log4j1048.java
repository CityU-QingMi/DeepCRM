    private RootLoggerComponentBuilder createRootLogger(final Properties properties) {
        final String level = Strings.trimToNull((String) properties.remove("level"));
        final String type = (String) properties.remove(CONFIG_TYPE);
        final String location = (String) properties.remove("includeLocation");
        final boolean includeLocation;
        final RootLoggerComponentBuilder loggerBuilder;
        if (type != null) {
            if (type.equalsIgnoreCase("asyncRoot")) {
                if (location != null) {
                    includeLocation = Boolean.parseBoolean(location);
                    loggerBuilder = builder.newAsyncRootLogger(level, includeLocation);
                } else {
                    loggerBuilder = builder.newAsyncRootLogger(level);
                }
            } else {
                throw new ConfigurationException("Unknown Logger type for root logger" + type);
            }
        } else {
            if (location != null) {
                includeLocation = Boolean.parseBoolean(location);
                loggerBuilder = builder.newRootLogger(level, includeLocation);
            } else {
                loggerBuilder = builder.newRootLogger(level);
            }
        }
        addLoggersToComponent(loggerBuilder, properties);
        return addFiltersToComponent(loggerBuilder, properties);
    }
