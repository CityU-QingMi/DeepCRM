    private Server prepareServer(Handler handler)
    {
        _threadPool = new QueuedThreadPool();
        _threadPool.setMinThreads(THREADS);
        _threadPool.setMaxThreads(THREADS);
        _threadPool.setDetailedDump(true);
        _server = new Server(_threadPool);
        int acceptors = 1;
        int selectors = 1;
        _connector = connectorProvider.newConnector(_server, acceptors, selectors);
        _server.addConnector(_connector);
        _server.setHandler(handler);
        return _server;
    }
