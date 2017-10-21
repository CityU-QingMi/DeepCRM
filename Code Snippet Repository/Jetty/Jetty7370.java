    @Test
    public void testOPTIONS() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertTrue(HttpMethod.OPTIONS.is(request.getMethod()));
                Assert.assertEquals("*", target);
                Assert.assertEquals("*", request.getPathInfo());
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .method(HttpMethod.OPTIONS)
                .path("*")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
