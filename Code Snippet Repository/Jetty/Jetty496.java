    @Test
    public void testExplicitConnectionIsClosedOnRemoteClose() throws Exception
    {
        start(new EmptyServerHandler());

        Destination destination = client.getDestination(scheme, "localhost", connector.getLocalPort());
        FuturePromise<Connection> futureConnection = new FuturePromise<>();
        destination.newConnection(futureConnection);
        Connection connection = futureConnection.get(5, TimeUnit.SECONDS);
        Request request = client.newRequest(destination.getHost(), destination.getPort()).scheme(scheme);
        FutureResponseListener listener = new FutureResponseListener(request);
        connection.send(request, listener);
        ContentResponse response = listener.get(5, TimeUnit.SECONDS);

        Assert.assertEquals(200, response.getStatus());

        // Wait some time to have the client is an idle state.
        TimeUnit.SECONDS.sleep(1);

        connector.stop();

        // Give the connection some time to process the remote close.
        TimeUnit.SECONDS.sleep(1);

        HttpConnectionOverHTTP httpConnection = (HttpConnectionOverHTTP)connection;
        Assert.assertFalse(httpConnection.getEndPoint().isOpen());

        HttpDestinationOverHTTP httpDestination = (HttpDestinationOverHTTP)destination;
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)httpDestination.getConnectionPool();
        Assert.assertTrue(connectionPool.getActiveConnections().isEmpty());
        Assert.assertTrue(connectionPool.getIdleConnections().isEmpty());
    }
