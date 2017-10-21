    public void startServer(Handler handler) throws Exception
    {
        server = new Server();
        HttpConfiguration configuration = new HttpConfiguration();
        connector = new ServerConnector(server, new ProxyConnectionFactory(), new HTTP2CServerConnectionFactory(configuration));
        server.addConnector(connector);
        server.setHandler(handler);

        client = new HTTP2Client();
        server.addBean(client, true);

        server.start();
    }
