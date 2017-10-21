    public void startServer(Handler handler) throws Exception
    {
        server = new Server();
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        HttpConnectionFactory h1 = new HttpConnectionFactory(httpConfiguration);
        HTTP2ServerConnectionFactory h2 = new HTTP2ServerConnectionFactory(httpConfiguration);
        ALPNServerConnectionFactory alpn = new ALPNServerConnectionFactory();
        alpn.setDefaultProtocol(h1.getProtocol());
        connector = new ServerConnector(server, newSslContextFactory(), alpn, h1, h2);
        server.addConnector(connector);
        server.setHandler(handler);
        server.start();
    }
