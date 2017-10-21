    @Test
    public void testProxyWithQueryString() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                resp.getOutputStream().print(req.getQueryString());
            }
        });
        startProxy();
        startClient();

        String query = "a=1&b=%E2%82%AC";
        ContentResponse response = client.newRequest("http://localhost:" + serverConnector.getLocalPort() + "/?" + query)
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(query, response.getContentAsString());
    }
