    @Test
    public void testIdleServerIdleTimeout() throws Exception
    {
        start(new EmptyServerHandler());
        connector.setIdleTimeout(idleTimeout);

        ContentResponse response1 = client.newRequest(newURI()).send();
        Assert.assertEquals(HttpStatus.OK_200, response1.getStatus());

        // Let the server idle timeout.
        Thread.sleep(2 * idleTimeout);

        // Make sure we can make another request successfully.
        ContentResponse response2 = client.newRequest(newURI()).send();
        Assert.assertEquals(HttpStatus.OK_200, response2.getStatus());
    }
