    public void prepare(Handler handler) throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server, new CAFEBABEServerConnectionFactory(new HttpConnectionFactory()));
        server.addConnector(connector);
        server.setHandler(handler);
        server.start();

        QueuedThreadPool executor = new QueuedThreadPool();
        executor.setName(executor.getName() + "-client");
        client = new HttpClient();
        client.setExecutor(executor);
        client.start();
    }
