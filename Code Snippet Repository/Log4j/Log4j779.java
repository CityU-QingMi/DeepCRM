    @PluginFactory
    @Deprecated
    public static DefaultRolloverStrategy createStrategy(
            // @formatter:off
            @PluginAttribute("max") final String max,
            @PluginAttribute("min") final String min,
            @PluginAttribute("fileIndex") final String fileIndex,
            @PluginAttribute("compressionLevel") final String compressionLevelStr,
            @PluginElement("Actions") final Action[] customActions,
            @PluginAttribute(value = "stopCustomActionsOnError", defaultBoolean = true)
                    final boolean stopCustomActionsOnError,
            @PluginConfiguration final Configuration config) {
        return DefaultRolloverStrategy.newBuilder()
                    .withMin(min)
                    .withMax(max)
                    .withFileIndex(fileIndex)
                    .withCompressionLevelStr(compressionLevelStr)
                    .withCustomActions(customActions)
                    .withStopCustomActionsOnError(stopCustomActionsOnError)
                    .withConfig(config)
                .build();
            // @formatter:on
    }
