    @Test
    public void testServerDown() throws Exception
    {
        prepareProxy();
        prepareServer(new EmptyHttpServlet());

        // Shutdown the server
        int serverPort = serverConnector.getLocalPort();
        server.stop();

        ContentResponse response = client.newRequest("localhost", serverPort)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(502, response.getStatus());
    }
