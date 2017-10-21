    @Deprecated
    public static AbstractJacksonLayout createLayout(
            final Configuration config,
            final boolean locationInfo,
            final boolean properties,
            final String headerPattern,
            final String footerPattern,
            final Charset charset,
            final boolean includeStacktrace) {
        return new YamlLayout(config, locationInfo, properties, false, false, true, headerPattern, footerPattern,
                charset, includeStacktrace, false, false);
    }
