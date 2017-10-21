    @Test
    public void testRedirectsAreProxied() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (req.getHeader("Via") != null)
                    resp.addHeader(PROXIED_HEADER, "true");
                resp.sendRedirect("/");
            }
        });
        startProxy();
        startClient();

        client.setFollowRedirects(false);

        ContentResponse response = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(302, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(PROXIED_HEADER));
    }
