    public SSLAsyncIOServletTest(SslContextFactory sslContextFactory)
    {
        this.sslContextFactory = sslContextFactory;
        if (sslContextFactory != null)
        {
            sslContextFactory.setEndpointIdentificationAlgorithm("");
            sslContextFactory.setKeyStorePath("src/test/resources/keystore.jks");
            sslContextFactory.setKeyStorePassword("storepwd");
            sslContextFactory.setTrustStorePath("src/test/resources/truststore.jks");
            sslContextFactory.setTrustStorePassword("storepwd");
        }
    }
