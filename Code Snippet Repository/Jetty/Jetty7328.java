    @Test
    public void testIdleClientIdleTimeout() throws Exception
    {
        start(new EmptyServerHandler());
        client.stop();
        client.setIdleTimeout(idleTimeout);
        client.start();

        // Make a first request to open a connection.
        ContentResponse response = client.newRequest(newURI()).send();
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());

        // Let the connection idle timeout.
        Thread.sleep(2 * idleTimeout);

        // Verify that after the timeout we can make another request.
        response = client.newRequest(newURI()).send();
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
