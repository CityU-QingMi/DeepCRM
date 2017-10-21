    protected void startClient() throws Exception
    {
        QueuedThreadPool clientThreads = new QueuedThreadPool();
        clientThreads.setName("client");
        clientThreads.setDetailedDump(true);
        client = newHttpClient(provideClientTransport(transport), sslContextFactory);
        client.setExecutor(clientThreads);
        client.setSocketAddressResolver(new SocketAddressResolver.Sync());
        client.start();
        if (server != null)
            server.addBean(client);
    }
