    @Test
    public void testProxyXForwardedHostHeaderIsPresent() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                PrintWriter writer = resp.getWriter();
                writer.write(req.getHeader("X-Forwarded-Host"));
                writer.flush();
            }
        });
        startProxy();
        startClient();

        ContentResponse response = client.GET("http://localhost:" + serverConnector.getLocalPort());
        Assert.assertThat("Response expected to contain content of X-Forwarded-Host Header from the request",
                response.getContentAsString(),
                Matchers.equalTo("localhost:" + serverConnector.getLocalPort()));
    }
