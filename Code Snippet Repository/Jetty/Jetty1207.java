    @Test
    public void testAbsoluteFormTarget() throws Exception
    {
        String path = "/path";
        String query = "a=b";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals(path, request.getRequestURI());
                Assert.assertEquals(query, request.getQueryString());
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .path("http://localhost:" + connector.getLocalPort() + path + "?" + query)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
