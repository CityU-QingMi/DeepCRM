    @Test
    public void testAbortBeforeQueued() throws Exception
    {
        start(new EmptyServerHandler());

        Exception failure = new Exception("oops");
        try
        {
            Request request = client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .timeout(5, TimeUnit.SECONDS);
            request.abort(failure);
            request.send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            Assert.assertSame(failure, x.getCause());
            // Make sure the pool is in a sane state.
            HttpDestination destination = (HttpDestination)client.getDestination(scheme, "localhost", connector.getLocalPort());
            DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();
            Assert.assertEquals(1, connectionPool.getConnectionCount());
            Assert.assertEquals(0, connectionPool.getActiveConnections().size());
            Assert.assertEquals(1, connectionPool.getIdleConnections().size());
        }
    }
