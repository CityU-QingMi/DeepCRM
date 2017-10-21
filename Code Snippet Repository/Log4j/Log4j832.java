    @PluginFactory
    public static IfAccumulatedFileSize createFileSizeCondition( 
            // @formatter:off
            @PluginAttribute("exceeds") final String size,
            @PluginElement("PathConditions") final PathCondition... nestedConditions) {
            // @formatter:on

        if (size == null) {
            LOGGER.error("IfAccumulatedFileSize missing mandatory size threshold.");
        }
        final long threshold = size == null ? Long.MAX_VALUE : FileSize.parse(size, Long.MAX_VALUE);
        return new IfAccumulatedFileSize(threshold, nestedConditions);
    }
