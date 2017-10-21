    protected AbstractStringLayout(final Configuration config, final Charset aCharset,
            final Serializer headerSerializer, final Serializer footerSerializer) {
        super(config, null, null);
        this.headerSerializer = headerSerializer;
        this.footerSerializer = footerSerializer;
        this.charset = aCharset == null ? StandardCharsets.UTF_8 : aCharset;
        this.charsetName = this.charset.name();
        useCustomEncoding = isPreJava8()
                && (StandardCharsets.ISO_8859_1.equals(aCharset) || StandardCharsets.US_ASCII.equals(aCharset));
        textEncoder = Constants.ENABLE_DIRECT_ENCODERS ? new StringBuilderEncoder(charset) : null;
    }
