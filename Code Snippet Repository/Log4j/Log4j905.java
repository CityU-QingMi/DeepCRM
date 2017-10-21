    public static ThreadNameCachingStrategy create() {
        final String name = PropertiesUtil.getProperties().getStringProperty("AsyncLogger.ThreadNameStrategy",
                CACHED.name());
        try {
            final ThreadNameCachingStrategy result = ThreadNameCachingStrategy.valueOf(name);
            LOGGER.debug("AsyncLogger.ThreadNameStrategy={}", result);
            return result;
        } catch (final Exception ex) {
            LOGGER.debug("Using AsyncLogger.ThreadNameStrategy.CACHED: '{}' not valid: {}", name, ex.toString());
            return CACHED;
        }
    }
