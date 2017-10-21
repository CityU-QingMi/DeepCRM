    @Test
    public void testRequestAfterValidation() throws Exception
    {
        start(new EmptyServerHandler());

        client.setMaxConnectionsPerDestination(1);

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .send();
        Assert.assertEquals(200, response.getStatus());

        // The second request should be sent after the validating timeout.
        response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .send();
        Assert.assertEquals(200, response.getStatus());
    }
