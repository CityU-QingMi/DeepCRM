    protected InetSocketAddress prepare() throws Exception
    {
        server = new Server();
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        HttpConnectionFactory h1 = new HttpConnectionFactory(httpConfiguration);
        HTTP2ServerConnectionFactory h2 = new HTTP2ServerConnectionFactory(httpConfiguration);
        ALPNServerConnectionFactory alpn = new ALPNServerConnectionFactory();
        alpn.setDefaultProtocol(h1.getProtocol());
        
        connector = new ServerConnector(server, newSslContextFactory(), alpn, h1, h2);
        connector.setPort(0);
        connector.setIdleTimeout(30000);
        server.addConnector(connector);
        server.start();

        ALPN.debug = true;

        return new InetSocketAddress("localhost", connector.getLocalPort());
    }
