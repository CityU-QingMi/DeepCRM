    public HttpURLConnectionManager(final Configuration configuration, final LoggerContext loggerContext, final String name,
                                    final URL url, final String method, final int connectTimeoutMillis,
                                    final int readTimeoutMillis,
                                    final Property[] headers,
                                    final SslConfiguration sslConfiguration,
                                    final boolean verifyHostname) {
        super(configuration, loggerContext, name);
        this.url = url;
        if (!(url.getProtocol().equalsIgnoreCase("http") || url.getProtocol().equalsIgnoreCase("https"))) {
            throw new ConfigurationException("URL must have scheme http or https");
        }
        this.isHttps = this.url.getProtocol().equalsIgnoreCase("https");
        this.method = Objects.requireNonNull(method, "method");
        this.connectTimeoutMillis = connectTimeoutMillis;
        this.readTimeoutMillis = readTimeoutMillis;
        this.headers = headers != null ? headers : new Property[0];
        this.sslConfiguration = sslConfiguration;
        if (this.sslConfiguration != null && !isHttps) {
            throw new ConfigurationException("SSL configuration can only be specified with URL scheme https");
        }
        this.verifyHostname = verifyHostname;
    }
