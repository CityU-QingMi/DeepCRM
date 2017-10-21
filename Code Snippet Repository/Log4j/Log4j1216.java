    protected AbstractStringLayout(final Charset aCharset, final byte[] header, final byte[] footer) {
        super(null, header, footer);
        this.headerSerializer = null;
        this.footerSerializer = null;
        this.charset = aCharset == null ? StandardCharsets.UTF_8 : aCharset;
        this.charsetName = this.charset.name();
        useCustomEncoding = isPreJava8()
                && (StandardCharsets.ISO_8859_1.equals(aCharset) || StandardCharsets.US_ASCII.equals(aCharset));
        textEncoder = Constants.ENABLE_DIRECT_ENCODERS ? new StringBuilderEncoder(charset) : null;
    }
