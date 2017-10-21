    @Test
    public void testAbortOnQueued() throws Exception
    {
        start(new EmptyServerHandler());

        final Throwable cause = new Exception();
        final AtomicBoolean aborted = new AtomicBoolean();
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean begin = new AtomicBoolean();
        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .listener(new Request.Listener.Adapter()
                    {
                        @Override
                        public void onQueued(Request request)
                        {
                            aborted.set(request.abort(cause));
                            latch.countDown();
                        }

                        @Override
                        public void onBegin(Request request)
                        {
                            begin.set(true);
                        }
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
            Assert.assertFalse(begin.get());
        }

        HttpDestinationOverHTTP destination = (HttpDestinationOverHTTP)client.getDestination(scheme, "localhost", connector.getLocalPort());
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();
        Assert.assertEquals(0, connectionPool.getConnectionCount());
        Assert.assertEquals(0, connectionPool.getActiveConnections().size());
        Assert.assertEquals(0, connectionPool.getIdleConnections().size());
    }
