    @Test
    public void testProxyWhiteList() throws Exception
    {
        startServer(new EmptyHttpServlet());
        startProxy();
        startClient();
        int port = serverConnector.getLocalPort();
        proxyServlet.getWhiteListHosts().add("127.0.0.1:" + port);

        // Try with the wrong host
        ContentResponse response = client.newRequest("localhost", port)
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(403, response.getStatus());

        // Try again with the right host
        response = client.newRequest("127.0.0.1", port)
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(200, response.getStatus());
    }
