    @Test
    public void testExplicitConnection() throws Exception
    {
        start(new EmptyServerHandler());

        Destination destination = client.getDestination(scheme, "localhost", connector.getLocalPort());
        FuturePromise<Connection> futureConnection = new FuturePromise<>();
        destination.newConnection(futureConnection);
        try (Connection connection = futureConnection.get(5, TimeUnit.SECONDS))
        {
            Request request = client.newRequest(destination.getHost(), destination.getPort()).scheme(scheme);
            FutureResponseListener listener = new FutureResponseListener(request);
            connection.send(request, listener);
            ContentResponse response = listener.get(5, TimeUnit.SECONDS);

            Assert.assertNotNull(response);
            Assert.assertEquals(200, response.getStatus());

            HttpDestinationOverHTTP httpDestination = (HttpDestinationOverHTTP)destination;
            DuplexConnectionPool connectionPool = (DuplexConnectionPool)httpDestination.getConnectionPool();
            Assert.assertTrue(connectionPool.getActiveConnections().isEmpty());
            Assert.assertTrue(connectionPool.getIdleConnections().isEmpty());
        }
    }
