    @Test
    public void testPathWithQueryAndParam() throws Exception
    {
        String name1 = "a";
        String value1 = "1";
        String name2 = "b";
        String value2 = "2";
        final String query = name1 + "=" + value1 + "&" + name2 + "=" + value2;
        final String path = "/path";
        String pathQuery = path + "?" + query;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(path, request.getRequestURI());
                Assert.assertEquals(query, request.getQueryString());
            }
        });

        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(5, TimeUnit.SECONDS)
                .path(path + "?" + name1 + "=" + value1)
                .param(name2, value2);

        Assert.assertEquals(path, request.getPath());
        Assert.assertEquals(query, request.getQuery());
        Assert.assertTrue(request.getURI().toString().endsWith(pathQuery));
        Fields params = request.getParams();
        Assert.assertEquals(2, params.getSize());
        Assert.assertEquals(value1, params.get(name1).getValue());
        Assert.assertEquals(value2, params.get(name2).getValue());

        ContentResponse response = request.send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
