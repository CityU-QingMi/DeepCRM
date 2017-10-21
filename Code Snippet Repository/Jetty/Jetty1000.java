    private Server startServer(String name, ServerSessionListener listener) throws Exception
    {
        QueuedThreadPool serverExecutor = new QueuedThreadPool();
        serverExecutor.setName(name);
        Server server = new Server(serverExecutor);
        RawHTTP2ServerConnectionFactory connectionFactory = new RawHTTP2ServerConnectionFactory(new HttpConfiguration(), listener);
        ServerConnector connector = new ServerConnector(server, 1, 1, connectionFactory);
        server.addConnector(connector);
        server.setAttribute("connector", connector);
        servers.add(server);
        server.start();
        return server;
    }
