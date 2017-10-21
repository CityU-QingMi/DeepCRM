    public synchronized void removeAppender(final String appenderName) {
        for (final LoggerConfig logger : loggerConfigs.values()) {
            logger.removeAppender(appenderName);
        }
        final Appender app = appenders.remove(appenderName);

        if (app != null) {
            app.stop();
        }
    }
