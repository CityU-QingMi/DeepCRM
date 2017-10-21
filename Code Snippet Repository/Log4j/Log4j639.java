    @Override
    public void start() {
        final Map<String, Appender> map = config.getAppenders();
        int errors = 0;
        final Appender appender = map.get(primaryRef);
        if (appender != null) {
            primary = new AppenderControl(appender, null, null);
        } else {
            LOGGER.error("Unable to locate primary Appender " + primaryRef);
            ++errors;
        }
        for (final String name : failovers) {
            final Appender foAppender = map.get(name);
            if (foAppender != null) {
                failoverAppenders.add(new AppenderControl(foAppender, null, null));
            } else {
                LOGGER.error("Failover appender " + name + " is not configured");
            }
        }
        if (failoverAppenders.isEmpty()) {
            LOGGER.error("No failover appenders are available");
            ++errors;
        }
        if (errors == 0) {
            super.start();
        }
    }
