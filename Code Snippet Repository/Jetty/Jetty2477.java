    @Test
    public void testClientExcludedHosts() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (req.getHeader("Via") != null)
                    resp.addHeader(PROXIED_HEADER, "true");
            }
        });
        startProxy();
        startClient();
        int port = serverConnector.getLocalPort();
        client.getProxyConfiguration().getProxies().get(0).getExcludedAddresses().add("127.0.0.1:" + port);

        // Try with a proxied host
        ContentResponse response = client.newRequest("localhost", port)
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(PROXIED_HEADER));

        // Try again with an excluded host
        response = client.newRequest("127.0.0.1", port)
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertFalse(response.getHeaders().containsKey(PROXIED_HEADER));
    }
