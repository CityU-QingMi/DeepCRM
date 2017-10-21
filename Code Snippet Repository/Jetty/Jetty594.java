    @Test
    public void testPath() throws Exception
    {
        final String path = "/path";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(path, request.getRequestURI());
            }
        });

        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(5, TimeUnit.SECONDS)
                .path(path);

        Assert.assertEquals(path, request.getPath());
        Assert.assertNull(request.getQuery());
        Fields params = request.getParams();
        Assert.assertEquals(0, params.getSize());
        Assert.assertTrue(request.getURI().toString().endsWith(path));

        ContentResponse response = request.send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
