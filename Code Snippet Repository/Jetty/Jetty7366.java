    private void testUploadWithoutResponseContent(int length) throws Exception
    {
        final byte[] bytes = new byte[length];
        new Random().nextBytes(bytes);
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                ServletInputStream input = request.getInputStream();
                for (int i = 0; i < bytes.length; ++i)
                    Assert.assertEquals(bytes[i] & 0xFF, input.read());
                Assert.assertEquals(-1, input.read());
            }
        });

        ContentResponse response = client.newRequest(newURI())
                .method(HttpMethod.POST)
                .content(new BytesContentProvider(bytes))
                .timeout(15, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(0, response.getContent().length);
    }
