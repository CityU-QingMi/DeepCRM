    @Test
    public void testRawQueryIsPreservedInURI() throws Exception
    {
        final String name = "a";
        final String rawValue = "Hello%20World";
        final String rawQuery = name + "=" + rawValue;
        final String value = "Hello World";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(rawQuery, request.getQueryString());
                Assert.assertEquals(value, request.getParameter(name));
            }
        });

        String uri = scheme + "://localhost:" + connector.getLocalPort() + "/path?" + rawQuery;
        Request request = client.newRequest(uri)
                .timeout(5, TimeUnit.SECONDS);
        Assert.assertEquals(rawQuery, request.getQuery());

        ContentResponse response = request.send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
