    @Test()
    public void testConnectorUsesServerExecutorWithNotEnoughThreads() throws Exception
    {
        try
        {
            // server has 3 threads in the executor
            _server = new Server(new QueuedThreadPool(3));

            // connector will use executor from server because connectorPool is null
            ThreadPool connectorPool = null;
            // connector requires 7 threads(2 + 4 + 1)
            ServerConnector connector = new ServerConnector(_server, connectorPool, null, null, 2, 4, new HttpConnectionFactory());
            connector.setPort(0);
            _server.addConnector(connector);

            // should throw IllegalStateException because there are no required threads in server pool
            _server.start();
            Assert.fail();
        }
        catch(IllegalStateException e)
        {
            Log.getLogger(ThreadBudget.class).warn(e.toString());
        }
    }
