    @Deprecated
    public static XmlLayout createLayout(
            final boolean locationInfo,
            final boolean properties,
            final boolean complete,
            final boolean compact,
            final Charset charset,
            final boolean includeStacktrace) {
        return new XmlLayout(null, locationInfo, properties, complete, compact, charset, includeStacktrace, false,
                false);
    }
