    @Test
    public void testPOSTWithContentNotifiesRequestContentListener() throws Exception
    {
        final byte[] content = {0, 1, 2, 3};
        start(new EmptyServerHandler());

        ContentResponse response = client.POST(scheme + "://localhost:" + connector.getLocalPort())
                .onRequestContent(new Request.ContentListener()
                {
                    @Override
                    public void onContent(Request request, ByteBuffer buffer)
                    {
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        if (!Arrays.equals(content, bytes))
                            request.abort(new Exception());
                    }
                })
                .content(new BytesContentProvider(content))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
