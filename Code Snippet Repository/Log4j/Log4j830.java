    @PluginFactory
    public static IfAccumulatedFileCount createFileCountCondition( 
            // @formatter:off
            @PluginAttribute(value = "exceeds", defaultInt = Integer.MAX_VALUE) final int threshold,
            @PluginElement("PathConditions") final PathCondition... nestedConditions) {
            // @formatter:on

        if (threshold == Integer.MAX_VALUE) {
            LOGGER.error("IfAccumulatedFileCount invalid or missing threshold value.");
        }
        return new IfAccumulatedFileCount(threshold, nestedConditions);
    }
