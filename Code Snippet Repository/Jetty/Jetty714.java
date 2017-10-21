    @Test
    public void testTypedContentProviderWithNoContentType() throws Exception
    {
        final String content = "data";

        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                Assert.assertEquals("GET", request.getMethod());
                Assert.assertNotNull(request.getContentType());
                Assert.assertEquals(content, IO.toString(request.getInputStream()));
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .content(new StringContentProvider(null, content, StandardCharsets.UTF_8))
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
