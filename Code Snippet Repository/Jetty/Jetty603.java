    @Test
    public void test_SuccessfulRequest_ReturnsConnection() throws Exception
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

        final CountDownLatch headersLatch = new CountDownLatch(1);
        final CountDownLatch successLatch = new CountDownLatch(3);
        client.newRequest(host, port)
                .scheme(scheme)
                .onRequestSuccess(request -> successLatch.countDown())
                .onResponseHeaders(response ->
                {
                    Assert.assertEquals(0, idleConnections.size());
                    Assert.assertEquals(1, activeConnections.size());
                    headersLatch.countDown();
                })
                .send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onSuccess(Response response)
                    {
                        successLatch.countDown();
                    }

                    @Override
                    public void onComplete(Result result)
                    {
                        Assert.assertFalse(result.isFailed());
                        successLatch.countDown();
                    }
                });

        Assert.assertTrue(headersLatch.await(30, TimeUnit.SECONDS));
        Assert.assertTrue(successLatch.await(30, TimeUnit.SECONDS));

        Assert.assertEquals(1, idleConnections.size());
        Assert.assertEquals(0, activeConnections.size());
    }
