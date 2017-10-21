    @Deprecated
    public static GelfLayout createLayout(
            //@formatter:off
            @PluginAttribute("host") final String host,
            @PluginElement("AdditionalField") final KeyValuePair[] additionalFields,
            @PluginAttribute(value = "compressionType",
                defaultString = "GZIP") final CompressionType compressionType,
            @PluginAttribute(value = "compressionThreshold",
                defaultInt = COMPRESSION_THRESHOLD) final int compressionThreshold,
            @PluginAttribute(value = "includeStacktrace",
                defaultBoolean = true) final boolean includeStacktrace) {
            // @formatter:on
        return new GelfLayout(null, host, additionalFields, compressionType, compressionThreshold, includeStacktrace, true, false);
    }
