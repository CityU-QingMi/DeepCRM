    protected void startTLSServer(Handler handler) throws Exception
    {
        QueuedThreadPool serverThreads = new QueuedThreadPool();
        serverThreads.setName("server");
        server = new Server(serverThreads);
        serverConnector = new ServerConnector(server, newSslContextFactory());
        server.addConnector(serverConnector);
        server.setHandler(handler);
        server.start();
    }
