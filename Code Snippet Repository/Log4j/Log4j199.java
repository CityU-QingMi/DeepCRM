    @Override
    public boolean isEnabled(final Level level, final Marker marker) {
        // LOG4J2-1813 if system property "log4j2.debug" is defined, all status logging is enabled
        if (isDebugPropertyEnabled()) {
            return true;
        }
        if (listeners.size() > 0) {
            return listenersLevel >= level.intLevel();
        }
        return logger.isEnabled(level, marker);
    }
