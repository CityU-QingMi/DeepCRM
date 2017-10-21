    @Test
    public void testRawQueryIsPreservedWithParam() throws Exception
    {
        final String name1 = "a";
        final String name2 = "b";
        final String rawValue1 = "Hello%20World";
        final String rawQuery1 = name1 + "=" + rawValue1;
        final String value1 = "Hello World";
        final String value2 = "alfa omega";
        final String encodedQuery2 = name2 + "=" + URLEncoder.encode(value2, "UTF-8");
        final String query = rawQuery1 + "&" + encodedQuery2;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(query, request.getQueryString());
                Assert.assertEquals(value1, request.getParameter(name1));
                Assert.assertEquals(value2, request.getParameter(name2));
            }
        });

        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/path?" + rawQuery1)
                .param(name2, value2)
                .timeout(5, TimeUnit.SECONDS);
        Assert.assertEquals(query, request.getQuery());

        ContentResponse response = request.send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
