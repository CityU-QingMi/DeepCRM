    @Deprecated
    @PluginFactory
    public static DirectWriteRolloverStrategy createStrategy(
            // @formatter:off
            @PluginAttribute("maxFiles") final String maxFiles,
            @PluginAttribute("compressionLevel") final String compressionLevelStr,
            @PluginElement("Actions") final Action[] customActions,
            @PluginAttribute(value = "stopCustomActionsOnError", defaultBoolean = true)
                    final boolean stopCustomActionsOnError,
            @PluginConfiguration final Configuration config) {
            return newBuilder().withMaxFiles(maxFiles)
                    .withCompressionLevelStr(compressionLevelStr)
                    .withCustomActions(customActions)
                    .withStopCustomActionsOnError(stopCustomActionsOnError)
                    .withConfig(config)
                    .build();
            // @formatter:on
    }
