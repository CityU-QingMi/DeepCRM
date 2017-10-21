    @Test
    public void testResponseWithContentType() throws Exception
    {
        final String content = "The quick brown fox jumped over the lazy dog";
        final String mediaType = "text/plain";
        final String encoding = "UTF-8";
        final String contentType = mediaType + "; charset=" + encoding;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setHeader(HttpHeader.CONTENT_TYPE.asString(), contentType);
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
        Assert.assertEquals(encoding, response.getEncoding());
    }
