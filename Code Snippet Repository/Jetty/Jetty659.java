    @Test
    public void testDestinationIsRemoved() throws Exception
    {
        String host = "localhost";
        int port = connector.getLocalPort();
        Destination destinationBefore = client.getDestination(scheme, host, port);

        ContentResponse response = client.newRequest(host, port)
                .scheme(scheme)
                .header(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString())
                .send();

        Assert.assertEquals(200, response.getStatus());

        Destination destinationAfter = client.getDestination(scheme, host, port);
        Assert.assertSame(destinationBefore, destinationAfter);

        client.setRemoveIdleDestinations(true);

        response = client.newRequest(host, port)
                .scheme(scheme)
                .header(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString())
                .send();

        Assert.assertEquals(200, response.getStatus());

        destinationAfter = client.getDestination(scheme, host, port);
        Assert.assertNotSame(destinationBefore, destinationAfter);
    }
