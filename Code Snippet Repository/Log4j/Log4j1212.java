    protected AbstractJacksonLayout(final Configuration config, final ObjectWriter objectWriter, final Charset charset,
            final boolean compact, final boolean complete, final boolean eventEol, final Serializer headerSerializer,
            final Serializer footerSerializer, final boolean includeNullDelimiter) {
        super(config, charset, headerSerializer, footerSerializer);
        this.objectWriter = objectWriter;
        this.compact = compact;
        this.complete = complete;
        this.eol = compact && !eventEol ? COMPACT_EOL : DEFAULT_EOL;
        this.includeNullDelimiter = includeNullDelimiter;
    }
