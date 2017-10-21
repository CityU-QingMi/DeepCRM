    @Test
    public void testCaseForMultipleConnectors() throws Exception
    {
        try
        {
            // server has 4 threads in the executor
            _server = new Server(new QueuedThreadPool(4));

            // first connector consumes 3 threads from server pool
            _server.addConnector(new ServerConnector(_server, null, null, null, 1, 1, new HttpConnectionFactory()));

            // second connect also require 4 threads but uses own executor, so its threads should not be counted
            final QueuedThreadPool connectorPool = new QueuedThreadPool(4, 4);
            _server.addConnector(new ServerConnector(_server, connectorPool, null, null, 1, 1, new HttpConnectionFactory()));

            // first connector consumes 3 threads from server pool
            _server.addConnector(new ServerConnector(_server, null, null, null, 1, 1, new HttpConnectionFactory()));

            // should not throw exception because limit was not overflown
            _server.start();

            Assert.fail();
        }
        catch(IllegalStateException e)
        {
            Log.getLogger(ThreadBudget.class).warn(e.toString());
        }
    }
