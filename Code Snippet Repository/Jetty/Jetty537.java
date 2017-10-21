    @Test
    public void test_ExchangeIsComplete_WhenRequestFails_WithNoResponse() throws Exception
    {
        start(new EmptyServerHandler());

        final CountDownLatch latch = new CountDownLatch(1);
        final String host = "localhost";
        final int port = connector.getLocalPort();
        client.newRequest(host, port)
                .scheme(scheme)
                .onRequestBegin(request ->
                {
                    HttpDestinationOverHTTP destination = (HttpDestinationOverHTTP)client.getDestination(scheme, host, port);
                    DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();
                    connectionPool.getActiveConnections().iterator().next().close();
                })
                .send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        latch.countDown();
                    }
                });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
