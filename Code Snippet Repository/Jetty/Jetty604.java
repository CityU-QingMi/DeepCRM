    @Test
    public void test_FailedRequest_RemovesConnection() throws Exception
    {
        start(new EmptyServerHandler());

        String host = "localhost";
        int port = connector.getLocalPort();
        HttpDestinationOverHTTP destination = (HttpDestinationOverHTTP)client.getDestination(scheme, host, port);
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();

        final Collection<Connection> idleConnections = connectionPool.getIdleConnections();
        Assert.assertEquals(0, idleConnections.size());

        final Collection<Connection> activeConnections = connectionPool.getActiveConnections();
        Assert.assertEquals(0, activeConnections.size());

        final CountDownLatch beginLatch = new CountDownLatch(1);
        final CountDownLatch failureLatch = new CountDownLatch(2);
        client.newRequest(host, port).scheme(scheme).listener(new Request.Listener.Adapter()
        {
            @Override
            public void onBegin(Request request)
            {
                activeConnections.iterator().next().close();
                beginLatch.countDown();
            }

            @Override
            public void onFailure(Request request, Throwable failure)
            {
                failureLatch.countDown();
            }
        }).send(new Response.Listener.Adapter()
        {
            @Override
            public void onComplete(Result result)
            {
                Assert.assertTrue(result.isFailed());
                Assert.assertEquals(0, idleConnections.size());
                Assert.assertEquals(0, activeConnections.size());
                failureLatch.countDown();
            }
        });

        Assert.assertTrue(beginLatch.await(30, TimeUnit.SECONDS));
        Assert.assertTrue(failureLatch.await(30, TimeUnit.SECONDS));

        Assert.assertEquals(0, idleConnections.size());
        Assert.assertEquals(0, activeConnections.size());
    }
