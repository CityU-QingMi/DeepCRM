    private void prepareServer(ConnectionFactory connectionFactory)
    {
        QueuedThreadPool serverExecutor = new QueuedThreadPool();
        serverExecutor.setName("server");
        server = new Server(serverExecutor);
        connector = new ServerConnector(server, connectionFactory);
        server.addConnector(connector);
        path = "/test";
        byteBufferPool = new MappedByteBufferPool();
        generator = new Generator(byteBufferPool);
    }
