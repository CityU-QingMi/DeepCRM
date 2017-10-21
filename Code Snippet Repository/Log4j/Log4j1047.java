    private LoggerComponentBuilder createLogger(final String key, final Properties properties) {
        final String name = (String) properties.remove(CONFIG_NAME);
        final String location = (String) properties.remove("includeLocation");
        if (Strings.isEmpty(name)) {
            throw new ConfigurationException("No name attribute provided for Logger " + key);
        }
        final String level = Strings.trimToNull((String) properties.remove("level"));
        final String type = (String) properties.remove(CONFIG_TYPE);
        final LoggerComponentBuilder loggerBuilder;
        boolean includeLocation;
        if (type != null) {
            if (type.equalsIgnoreCase("asyncLogger")) {
                if (location != null) {
                    includeLocation = Boolean.parseBoolean(location);
                    loggerBuilder = builder.newAsyncLogger(name, level, includeLocation);
                } else {
                    loggerBuilder = builder.newAsyncLogger(name, level);
                }
            } else {
                throw new ConfigurationException("Unknown Logger type " + type + " for Logger " + name);
            }
        } else {
            if (location != null) {
                includeLocation = Boolean.parseBoolean(location);
                loggerBuilder = builder.newLogger(name, level, includeLocation);
            } else {
                loggerBuilder = builder.newLogger(name, level);
            }
        }
        addLoggersToComponent(loggerBuilder, properties);
        addFiltersToComponent(loggerBuilder, properties);
        final String additivity = (String) properties.remove("additivity");
        if (!Strings.isEmpty(additivity)) {
            loggerBuilder.addAttribute("additivity", additivity);
        }
        return loggerBuilder;
    }
