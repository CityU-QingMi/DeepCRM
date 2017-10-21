    @Test
    public void testStoppingClosesConnections() throws Exception
    {
        start(new EmptyServerHandler());

        String host = "localhost";
        int port = connector.getLocalPort();
        String path = "/";
        Response response = client.GET(scheme + "://" + host + ":" + port + path);
        Assert.assertEquals(200, response.getStatus());

        HttpDestinationOverHTTP destination = (HttpDestinationOverHTTP)client.getDestination(scheme, host, port);
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();

        long start = System.nanoTime();
        HttpConnectionOverHTTP connection = null;
        while (connection == null && TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start) < 5)
        {
            connection = (HttpConnectionOverHTTP)connectionPool.getIdleConnections().peek();
            TimeUnit.MILLISECONDS.sleep(10);
        }
        Assert.assertNotNull(connection);

        String uri = destination.getScheme() + "://" + destination.getHost() + ":" + destination.getPort();
        client.getCookieStore().add(URI.create(uri), new HttpCookie("foo", "bar"));

        client.stop();

        Assert.assertEquals(0, client.getDestinations().size());
        Assert.assertEquals(0, connectionPool.getIdleConnectionCount());
        Assert.assertEquals(0, connectionPool.getActiveConnectionCount());
        Assert.assertFalse(connection.getEndPoint().isOpen());
    }
