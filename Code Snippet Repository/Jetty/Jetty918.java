    @Test
    public void testHTTPSRequestIsForwarded() throws Exception
    {
        final String path = "/one/";
        prepare(new HttpServlet()
        {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                Assert.assertTrue("https".equalsIgnoreCase(req.getScheme()));
                Assert.assertTrue(req.isSecure());
                Assert.assertEquals(forwardPath, req.getRequestURI());
                Assert.assertTrue(req.getQueryString().endsWith(path));
            }
        });

        ContentResponse response = client.newRequest("localhost", sslConnector.getLocalPort())
                .scheme("https")
                .path(path)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
