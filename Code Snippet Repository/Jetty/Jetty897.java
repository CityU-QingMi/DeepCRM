    @Test
    public void testPOSTWithContentTracksProgress() throws Exception
    {
        start(new EmptyServerHandler());

        final AtomicInteger progress = new AtomicInteger();
        ContentResponse response = client.POST(scheme + "://localhost:" + connector.getLocalPort())
                .onRequestContent(new Request.ContentListener()
                {
                    @Override
                    public void onContent(Request request, ByteBuffer buffer)
                    {
                        byte[] bytes = new byte[buffer.remaining()];
                        Assert.assertEquals(1, bytes.length);
                        buffer.get(bytes);
                        Assert.assertEquals(bytes[0], progress.getAndIncrement());
                    }
                })
                .content(new BytesContentProvider(new byte[]{0}, new byte[]{1}, new byte[]{2}, new byte[]{3}, new byte[]{4}))
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(5, progress.get());
    }
