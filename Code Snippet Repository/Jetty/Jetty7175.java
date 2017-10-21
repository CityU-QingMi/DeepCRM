    public ExampleEchoServer(int port)
    {
        server = new Server();
        connector = new ServerConnector(server);
        connector.setPort(port);

        server.addConnector(connector);
        wsHandler = new EchoSocketHandler();

        server.setHandler(wsHandler);

        rHandler = new ResourceHandler();
        rHandler.setDirectoriesListed(true);
        rHandler.setResourceBase("src/test/webapp");
        wsHandler.setHandler(rHandler);
    }
