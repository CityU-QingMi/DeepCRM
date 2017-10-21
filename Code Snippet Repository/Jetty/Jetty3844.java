    private void start(Handler handler) throws Exception
    {
        server = new Server();

        sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(KEYSTORE_1);
        sslContextFactory.setKeyStorePassword("storepwd");
        sslContextFactory.setKeyStoreType("JKS");
        sslContextFactory.setKeyStoreProvider(null);

        HttpConfiguration httpsConfig = new HttpConfiguration();
        httpsConfig.addCustomizer(new SecureRequestCustomizer());
        connector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(httpsConfig));
        server.addConnector(connector);

        server.setHandler(handler);

        server.start();
    }
