    private void testTransparentProxyWithQuery(String proxyToContext, String prefix, String target) throws Exception
    {
        final String query = "a=1&b=2";
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (req.getHeader("Via") != null)
                    resp.addHeader(PROXIED_HEADER, "true");

                String expectedURI = proxyToContext + target;
                if (expectedURI.isEmpty())
                    expectedURI = "/";
                if (expectedURI.equals(req.getRequestURI()))
                {
                    if (query.equals(req.getQueryString()))
                    {
                        resp.setStatus(200);
                        return;
                    }
                }
                resp.setStatus(404);
            }
        });
        String proxyTo = "http://localhost:" + serverConnector.getLocalPort() + proxyToContext;
        proxyServlet = new ProxyServlet.Transparent();
        Map<String, String> params = new HashMap<>();
        params.put("proxyTo", proxyTo);
        params.put("prefix", prefix);
        startProxy(params);
        startClient();

        // Make the request to the proxy, it should transparently forward to the server
        ContentResponse response = client.newRequest("localhost", proxyConnector.getLocalPort())
                .path(prefix + target + "?" + query)
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(PROXIED_HEADER));
    }
