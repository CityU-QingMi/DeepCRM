    @Test
    public void testAbortOnCommit() throws Exception
    {
        start(new EmptyServerHandler());

        // Test can behave in 2 ways:
        // A) the request is failed before the response arrived
        // B) the request is failed after the response arrived

        final Throwable cause = new Exception();
        final AtomicBoolean aborted = new AtomicBoolean();
        final CountDownLatch latch = new CountDownLatch(1);
        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .onRequestCommit(request ->
                    {
                        aborted.set(request.abort(cause));
                        latch.countDown();
                    })
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
            if (aborted.get())
                Assert.assertSame(cause, x.getCause());
        }

        HttpDestinationOverHTTP destination = (HttpDestinationOverHTTP)client.getDestination(scheme, "localhost", connector.getLocalPort());
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();
        Assert.assertEquals(0, connectionPool.getConnectionCount());
        Assert.assertEquals(0, connectionPool.getActiveConnections().size());
        Assert.assertEquals(0, connectionPool.getIdleConnections().size());
    }
