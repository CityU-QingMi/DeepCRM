    public SerializationParams(HttpServletResponse response, String encoding, boolean wrapWithComments,
            String serializedJSON, boolean smd, boolean gzip, boolean noCache, int statusCode, int errorCode,
            boolean prefix, String contentType, String wrapPrefix, String wrapSuffix) {
        this.response = response;
        this.encoding = encoding;
        this.wrapWithComments = wrapWithComments;
        this.serializedJSON = serializedJSON;
        this.smd = smd;
        this.gzip = gzip;
        this.noCache = noCache;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.prefix = prefix;
        this.contentType = StringUtils.defaultString(contentType, DEFAULT_CONTENT_TYPE);
        this.wrapPrefix = wrapPrefix;
        this.wrapSuffix = wrapSuffix;
    }
