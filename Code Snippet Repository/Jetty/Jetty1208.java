    @Test
    public void testRequestViaForwardHttpProxy() throws Exception
    {
        String path = "/path";
        String query = "a=b";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(path, request.getRequestURI());
                Assert.assertEquals(query, request.getQueryString());
            }
        });

        int proxyPort = connector.getLocalPort();
        client.getProxyConfiguration().getProxies().add(new HttpProxy("localhost", proxyPort));

        int serverPort = proxyPort + 1; // Any port will do, just not the same as the proxy.
        ContentResponse response = client.newRequest("localhost", serverPort)
                .path(path + "?" + query)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
