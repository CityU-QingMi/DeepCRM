    @Test
    public void testConnectorWithDedicatedExecutor() throws Exception
    {
        // server has 3 threads in the executor
        _server = new Server(new QueuedThreadPool(3));

        // connector pool has 100 threads
        ThreadPool connectorPool = new QueuedThreadPool(100);
        // connector requires 7 threads(2 + 4 + 1)
        ServerConnector connector = new ServerConnector(_server, connectorPool, null, null, 2, 4, new HttpConnectionFactory());
        connector.setPort(0);
        _server.addConnector(connector);

        // should not throw exception because connector uses own executor, so its threads should not be counted
        _server.start();
    }
