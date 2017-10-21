    @Test
    public void testResponseWithMediaType() throws Exception
    {
        final String content = "The quick brown fox jumped over the lazy dog";
        final String mediaType = "text/plain";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setHeader(HttpHeader.CONTENT_TYPE.asString(), mediaType);
                response.getOutputStream().write(content.getBytes("UTF-8"));
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(content, response.getContentAsString());
        Assert.assertEquals(mediaType, response.getMediaType());
        Assert.assertNull(response.getEncoding());
    }
