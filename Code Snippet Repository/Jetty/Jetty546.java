    @Test
    public void testUserAgentCanBeRemoved() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                ArrayList<String> userAgents = Collections.list(request.getHeaders("User-Agent"));
                if ("/ua".equals(target))
                    Assert.assertEquals(1, userAgents.size());
                else
                    Assert.assertEquals(0, userAgents.size());
            }
        });

        // User agent not specified, use default.
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/ua")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());

        // User agent explicitly removed.
        response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .agent(null)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());

        // User agent explicitly removed.
        response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .header(HttpHeader.USER_AGENT, null)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
