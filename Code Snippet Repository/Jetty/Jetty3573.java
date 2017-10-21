    public void initConnector(Handler handler) throws Exception
    {
        server = new Server();

        connector = new NetworkTrafficServerConnector(server);
        connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendDateHeader(false);
        connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);
        server.addConnector(connector);
        server.setHandler(handler);
        server.start();
    }
