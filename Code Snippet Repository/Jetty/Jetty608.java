    @Test
    public void testConnectionForHTTP10ResponseIsRemoved() throws Exception
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

        client.setStrictEventOrdering(false);
        ContentResponse response = client.newRequest(host, port)
                .scheme(scheme)
                .onResponseBegin(response1 ->
                {
                    // Simulate a HTTP 1.0 response has been received.
                    ((HttpResponse)response1).version(HttpVersion.HTTP_1_0);
                })
                .send();

        Assert.assertEquals(200, response.getStatus());

        Assert.assertEquals(0, idleConnections.size());
        Assert.assertEquals(0, activeConnections.size());
    }
