    private void startServer(Handler handler) throws Exception
    {
        QueuedThreadPool serverThreads = new QueuedThreadPool();
        serverThreads.setName("server");
        server = new Server(serverThreads);
        HttpConfiguration httpsConfig = new HttpConfiguration();
        httpsConfig.addCustomizer(new SecureRequestCustomizer());
        ConnectionFactory h2 = new HTTP2ServerConnectionFactory(httpsConfig);
        ConnectionFactory ssl = new SslConnectionFactory(newSslContextFactory(), h2.getProtocol());
        connector = new ServerConnector(server, 1, 1, ssl, h2);
        server.addConnector(connector);
        server.setHandler(handler);
        server.start();
    }
