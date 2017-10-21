    @Test
    public void testCustomProxy() throws Exception
    {
        final String serverHost = "server";
        final int status = HttpStatus.NO_CONTENT_204;
        prepare(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                if (serverHost.equals(request.getServerName()))
                    response.setStatus(status);
                else
                    response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
        });

        // Setup the custom proxy
        int proxyPort = connector.getLocalPort();
        int serverPort = proxyPort + 1; // Any port will do for these tests - just not the same as the proxy
        client.getProxyConfiguration().getProxies().add(new CAFEBABEProxy(new Origin.Address("localhost", proxyPort), false));

        ContentResponse response = client.newRequest(serverHost, serverPort)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(status, response.getStatus());
    }
