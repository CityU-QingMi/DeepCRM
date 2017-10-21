    @Test
    public void test_DestinationCount() throws Exception
    {
        start(new EmptyServerHandler());

        String host = "localhost";
        int port = connector.getLocalPort();
        client.GET(scheme + "://" + host + ":" + port);

        List<Destination> destinations = client.getDestinations();
        Assert.assertNotNull(destinations);
        Assert.assertEquals(1, destinations.size());
        Destination destination = destinations.get(0);
        Assert.assertNotNull(destination);
        Assert.assertEquals(scheme, destination.getScheme());
        Assert.assertEquals(host, destination.getHost());
        Assert.assertEquals(port, destination.getPort());
    }
