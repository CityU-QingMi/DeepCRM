    @Test
    public void testAsteriskFormTarget() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals("*", target);
                Assert.assertEquals("*", request.getPathInfo());
            }
        });

        Request request = client.newRequest("localhost", connector.getLocalPort())
                .method(HttpMethod.OPTIONS)
                .scheme(scheme)
                .path("*")
                .timeout(5, TimeUnit.SECONDS);

        Assert.assertEquals("*", request.getPath());
        Assert.assertNull(request.getQuery());
        Fields params = request.getParams();
        Assert.assertEquals(0, params.getSize());
        Assert.assertNull(request.getURI());

        ContentResponse response = request.send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
