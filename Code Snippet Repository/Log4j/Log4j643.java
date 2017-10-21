    @PluginFactory
    public static FailoverAppender createAppender(
            @PluginAttribute("name") final String name,
            @PluginAttribute("primary") final String primary,
            @PluginElement("Failovers") final String[] failovers,
            @PluginAliases("retryInterval") // deprecated
            @PluginAttribute("retryIntervalSeconds") final String retryIntervalSeconds,
            @PluginConfiguration final Configuration config,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("ignoreExceptions") final String ignore) {
        if (name == null) {
            LOGGER.error("A name for the Appender must be specified");
            return null;
        }
        if (primary == null) {
            LOGGER.error("A primary Appender must be specified");
            return null;
        }
        if (failovers == null || failovers.length == 0) {
            LOGGER.error("At least one failover Appender must be specified");
            return null;
        }

        final int seconds = parseInt(retryIntervalSeconds, DEFAULT_INTERVAL_SECONDS);
        int retryIntervalMillis;
        if (seconds >= 0) {
            retryIntervalMillis = seconds * Constants.MILLIS_IN_SECONDS;
        } else {
            LOGGER.warn("Interval " + retryIntervalSeconds + " is less than zero. Using default");
            retryIntervalMillis = DEFAULT_INTERVAL_SECONDS * Constants.MILLIS_IN_SECONDS;
        }

        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);

        return new FailoverAppender(name, filter, primary, failovers, retryIntervalMillis, config, ignoreExceptions);
    }
