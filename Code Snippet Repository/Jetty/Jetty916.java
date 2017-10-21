    @Test
    public void testURIRewrite() throws Exception
    {
        String originalPath = "/original/index.php";
        String originalQuery = "foo=bar";
        String remotePath = "/remote/index.php";
        prepare(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Assert.assertThat((String)request.getAttribute(FCGI.Headers.REQUEST_URI), Matchers.startsWith(originalPath));
                Assert.assertEquals(originalQuery, request.getAttribute(FCGI.Headers.QUERY_STRING));
                Assert.assertThat(request.getRequestURI(), Matchers.endsWith(remotePath));
            }
        });
        context.stop();
        String pathAttribute = "_path_attribute";
        String queryAttribute = "_query_attribute";
        ServletHolder fcgi = context.getServletHandler().getServlet("fcgi");
        fcgi.setInitParameter(FastCGIProxyServlet.ORIGINAL_URI_ATTRIBUTE_INIT_PARAM, pathAttribute);
        fcgi.setInitParameter(FastCGIProxyServlet.ORIGINAL_QUERY_ATTRIBUTE_INIT_PARAM, queryAttribute);
        context.insertHandler(new HandlerWrapper()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                if (target.startsWith("/remote/"))
                {
                    request.setAttribute(pathAttribute, originalPath);
                    request.setAttribute(queryAttribute, originalQuery);
                }
                super.handle(target, baseRequest, request, response);
            }
        });
        context.start();

        ContentResponse response = client.newRequest("localhost", httpConnector.getLocalPort())
                .path(remotePath)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
