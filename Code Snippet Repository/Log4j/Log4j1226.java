    private GelfLayout(final Configuration config, final String host, final KeyValuePair[] additionalFields, final CompressionType compressionType,
               final int compressionThreshold, final boolean includeStacktrace, final boolean includeThreadContext, final boolean includeNullDelimiter) {
        super(config, StandardCharsets.UTF_8, null, null);
        this.host = host != null ? host : NetUtils.getLocalHostname();
        this.additionalFields = additionalFields != null ? additionalFields : new KeyValuePair[0];
        if (config == null) {
            for (final KeyValuePair additionalField : this.additionalFields) {
                if (valueNeedsLookup(additionalField.getValue())) {
                    throw new IllegalArgumentException("configuration needs to be set when there are additional fields with variables");
                }
            }
        }
        this.compressionType = compressionType;
        this.compressionThreshold = compressionThreshold;
        this.includeStacktrace = includeStacktrace;
        this.includeThreadContext = includeThreadContext;
        this.includeNullDelimiter = includeNullDelimiter;
        if (includeNullDelimiter && compressionType != CompressionType.OFF) {
            throw new IllegalArgumentException("null delimiter cannot be used with compression");
        }
    }
