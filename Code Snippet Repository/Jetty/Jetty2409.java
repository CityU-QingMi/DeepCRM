    @Test
    public void testZeroContentLength() throws Exception
    {
        startServer(new EchoHttpServlet());
        startProxy(new AsyncMiddleManServlet());
        startClient();

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
