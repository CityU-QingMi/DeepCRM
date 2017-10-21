    @PluginFactory
    public static PurgePolicy createPurgePolicy(
        @PluginAttribute("timeToLive") final String timeToLive,
        @PluginAttribute("checkInterval") final String checkInterval,
        @PluginAttribute("timeUnit") final String timeUnit,
        @PluginConfiguration final Configuration configuration) {

        if (timeToLive == null) {
            LOGGER.error("A timeToLive value is required");
            return null;
        }
        TimeUnit units;
        if (timeUnit == null) {
            units = TimeUnit.MINUTES;
        } else {
            try {
                units = TimeUnit.valueOf(timeUnit.toUpperCase());
            } catch (final Exception ex) {
                LOGGER.error("Invalid timeUnit value {}. timeUnit set to MINUTES", timeUnit, ex);
                units = TimeUnit.MINUTES;
            }
        }

        long ttl = units.toMillis(Long.parseLong(timeToLive));
        if (ttl < 0) {
            LOGGER.error("timeToLive must be positive. timeToLive set to 0");
            ttl = 0;
        }
        
        long ci;
        if (checkInterval == null) {
            ci = ttl;
        } else {
            ci = units.toMillis(Long.parseLong(checkInterval));
            if (ci < 0) {
                LOGGER.error("checkInterval must be positive. checkInterval set equal to timeToLive = {}", ttl);
                ci = ttl;
            }
        }

        return new IdlePurgePolicy(ttl, ci, configuration.getScheduler());
    }
