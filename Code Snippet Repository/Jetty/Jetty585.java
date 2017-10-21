    @Test
    public void testCaseSensitiveParameterName() throws Exception
    {
        final String name1 = "a";
        final String name2 = "A";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(name1, request.getParameter(name1));
                Assert.assertEquals(name2, request.getParameter(name2));
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/path?" + name1 + "=" + name1)
                .param(name2, name2)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
