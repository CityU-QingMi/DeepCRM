    @Test
    public void test_POST_WithContent_NotifiesRequestContentListener() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                consume(request.getInputStream(), true);
            }
        });

        final byte[] content = {0, 1, 2, 3};
        ContentResponse response = client.POST(scheme + "://localhost:" + connector.getLocalPort())
                .onRequestContent((request, buffer) ->
                {
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    if (!Arrays.equals(content, bytes))
                        request.abort(new Exception());
                })
                .content(new BytesContentProvider(content))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
