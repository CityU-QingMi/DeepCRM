    private HTTP2Client startClient(String name) throws Exception
    {
        HTTP2Client client = new HTTP2Client();
        QueuedThreadPool clientExecutor = new QueuedThreadPool();
        clientExecutor.setName(name);
        client.setExecutor(clientExecutor);
        clients.add(client);
        client.start();
        return client;
    }
