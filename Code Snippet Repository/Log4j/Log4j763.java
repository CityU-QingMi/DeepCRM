    @PluginFactory
    public static LoggerNameLevelRewritePolicy createPolicy(
            // @formatter:off
            @PluginAttribute("logger") final String loggerNamePrefix,
            @PluginElement("KeyValuePair") final KeyValuePair[] levelPairs) {
            // @formatter:on
        final Map<Level, Level> newMap = new HashMap<>(levelPairs.length);
        for (final KeyValuePair keyValuePair : levelPairs) {
            newMap.put(getLevel(keyValuePair.getKey()), getLevel(keyValuePair.getValue()));
        }
        return new LoggerNameLevelRewritePolicy(loggerNamePrefix, newMap);
    }
