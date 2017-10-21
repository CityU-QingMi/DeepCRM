    public void start(Handler handler) throws Exception
    {
        server = new Server();

        ServerFCGIConnectionFactory fcgiConnectionFactory = new ServerFCGIConnectionFactory(new HttpConfiguration());
        serverBufferPool = new LeakTrackingByteBufferPool(new MappedByteBufferPool.Tagged());
        connector = new ServerConnector(server, null, null, serverBufferPool,
                1, Math.max(1, Runtime.getRuntime().availableProcessors() / 2), fcgiConnectionFactory);
//        connector.setPort(9000);

        server.addConnector(connector);
        server.setHandler(handler);
        server.start();

        QueuedThreadPool executor = new QueuedThreadPool();
        executor.setName(executor.getName() + "-client");

        HttpClientTransport transport = new HttpClientTransportOverFCGI(1, false, "");
        transport.setConnectionPoolFactory(destination -> new LeakTrackingConnectionPool(destination, client.getMaxConnectionsPerDestination(), destination)
        {
            @Override
            protected void leaked(LeakDetector.LeakInfo leakInfo)
            {
                connectionLeaks.incrementAndGet();
            }
        });
        client = new HttpClient(transport, null);
        client.setExecutor(executor);
        clientBufferPool = new LeakTrackingByteBufferPool(new MappedByteBufferPool.Tagged());
        client.setByteBufferPool(clientBufferPool);
        client.start();
    }
