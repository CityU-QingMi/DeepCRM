    public SSLEngine newSSLEngine()
    {
        checkIsStarted();

        SSLContext context = getSslContext();
        SSLEngine sslEngine = context.createSSLEngine();
        customize(sslEngine);

        return sslEngine;
    }
