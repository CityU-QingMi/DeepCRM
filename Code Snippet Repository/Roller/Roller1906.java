    private static boolean getBooleanProperty(String propName, boolean defaultValue) {
        String configuredVal = WebloggerConfig.getProperty(propName);
        if (configuredVal == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("PingConfig property '" + propName + "' is not present in the configuration.  Using default value: " + defaultValue);
            }
            return defaultValue;
        }
        return Boolean.valueOf(configuredVal);
    }
