    private static Clock createClock() {
        final String userRequest = PropertiesUtil.getProperties().getStringProperty(PROPERTY_NAME);
        if (userRequest == null || "SystemClock".equals(userRequest)) {
            LOGGER.trace("Using default SystemClock for timestamps.");
            return new SystemClock();
        }
        if (CachedClock.class.getName().equals(userRequest)
                || "CachedClock".equals(userRequest)) {
            LOGGER.trace("Using specified CachedClock for timestamps.");
            return CachedClock.instance();
        }
        if (CoarseCachedClock.class.getName().equals(userRequest)
                || "CoarseCachedClock".equals(userRequest)) {
            LOGGER.trace("Using specified CoarseCachedClock for timestamps.");
            return CoarseCachedClock.instance();
        }
        try {
            final Clock result = Loader.newCheckedInstanceOf(userRequest, Clock.class);
            LOGGER.trace("Using {} for timestamps.", result.getClass().getName());
            return result;
        } catch (final Exception e) {
            final String fmt = "Could not create {}: {}, using default SystemClock for timestamps.";
            LOGGER.error(fmt, userRequest, e);
            return new SystemClock();
        }
    }
