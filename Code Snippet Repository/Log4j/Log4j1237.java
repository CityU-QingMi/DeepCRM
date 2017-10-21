    @Deprecated
    public static JsonLayout createLayout(
            final Configuration config,
            final boolean locationInfo,
            final boolean properties,
            final boolean propertiesAsList,
            final boolean complete,
            final boolean compact,
            final boolean eventEol,
            final String headerPattern,
            final String footerPattern,
            final Charset charset,
            final boolean includeStacktrace) {
        final boolean encodeThreadContextAsList = properties && propertiesAsList;
        return new JsonLayout(config, locationInfo, properties, encodeThreadContextAsList, complete, compact, eventEol,
                headerPattern, footerPattern, charset, includeStacktrace, false, false);
    }
