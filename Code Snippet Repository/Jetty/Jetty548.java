    @Test
    public void testCustomHostHeader() throws Exception
    {
        final String host = "localhost";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(host, request.getServerName());
            }
        });

        ContentResponse response = client.newRequest("http://127.0.0.1:" + connector.getLocalPort() + "/path")
                .scheme(scheme)
                .header(HttpHeader.HOST, host)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
