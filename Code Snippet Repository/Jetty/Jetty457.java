    @Test
    public void test_ClientConnectionClose_ServerDoesNotRespond_ClientIdleTimeout() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                request.startAsync();
                // Do not respond.
            }
        });

        String host = "localhost";
        int port = connector.getLocalPort();

        HttpDestinationOverHTTP destination = (HttpDestinationOverHTTP)client.getDestination(scheme, host, port);
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();

        CountDownLatch resultLatch = new CountDownLatch(1);
        long idleTimeout = 1000;
        client.newRequest(host, port)
                .scheme(scheme)
                .header(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString())
                .idleTimeout(idleTimeout, TimeUnit.MILLISECONDS)
                .onRequestSuccess(request ->
                {
                    HttpConnectionOverHTTP connection = (HttpConnectionOverHTTP)connectionPool.getActiveConnections().iterator().next();
                    Assert.assertFalse(connection.getEndPoint().isOutputShutdown());
                })
                .send(result ->
                {
                    if (result.isFailed())
                        resultLatch.countDown();
                });

        Assert.assertTrue(resultLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
        Assert.assertEquals(0, connectionPool.getConnectionCount());
    }
