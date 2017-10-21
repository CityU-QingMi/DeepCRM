    private JsonLayout(final Configuration config, final boolean locationInfo, final boolean properties,
                       final boolean encodeThreadContextAsList,
                       final boolean complete, final boolean compact, final boolean eventEol,
                       final String headerPattern,final String footerPattern, final Charset charset,
                       final boolean includeStacktrace,final boolean stacktraceAsString,
                       final boolean includeNullDelimiter) {
        super(config, new JacksonFactory.JSON(encodeThreadContextAsList, includeStacktrace, stacktraceAsString).newWriter(
                locationInfo, properties, compact),
                charset, compact, complete, eventEol,
                PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(headerPattern).setDefaultPattern(DEFAULT_HEADER).build(),
                PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(footerPattern).setDefaultPattern(DEFAULT_FOOTER).build(),
                includeNullDelimiter);
    }
