    public SSLEngine newSSLEngine(String host, int port)
    {
        checkIsStarted();

        SSLContext context = getSslContext();
        SSLEngine sslEngine = isSessionCachingEnabled() ?
                context.createSSLEngine(host, port) :
                context.createSSLEngine();
        customize(sslEngine);

        return sslEngine;
    }
