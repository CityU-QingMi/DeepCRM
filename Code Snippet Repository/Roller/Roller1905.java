    private static int getIntegerProperty(String propName, int defaultValue, int min, int max) {
        String configuredVal = WebloggerConfig.getProperty(propName);
        if (configuredVal == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("PingConfig property '" + propName + "' is not present in the configuration.  Using default value: " + defaultValue);
            }
            return defaultValue;
        }

        int val;
        try {
            val = Integer.parseInt(configuredVal);
        } catch (NumberFormatException ex) {
            LOGGER.error("ERROR: PingConfig property '" + propName + "' is not an integer value.  Using default value: " + defaultValue);
            return defaultValue;
        }

        if (val < min || val > max) {
            LOGGER.error("ERROR: PingConfig property '" + propName + "' is outside the required range (" + min + ", " + max + ").  Using default value: " + defaultValue);
            return defaultValue;
        }

        return val;
    }
