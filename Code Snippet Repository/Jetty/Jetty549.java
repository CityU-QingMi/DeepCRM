    @Test
    public void testHTTP10WithKeepAliveAndContentLength() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                // Send the headers at this point, then write the content
                byte[] content = "TEST".getBytes("UTF-8");
                response.setContentLength(content.length);
                response.flushBuffer();
                response.getOutputStream().write(content);
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .version(HttpVersion.HTTP_1_0)
                .header(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.asString())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().contains(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.asString()));
    }
