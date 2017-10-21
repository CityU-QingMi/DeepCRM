    @Test
    public void testUploadWithConnectFailureClosesStream() throws Exception
    {
        start(new EmptyServerHandler());

        final CountDownLatch closeLatch = new CountDownLatch(1);
        InputStream stream = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8))
        {
            @Override
            public void close() throws IOException
            {
                super.close();
                closeLatch.countDown();
            }
        };
        InputStreamContentProvider content = new InputStreamContentProvider(stream);

        final CountDownLatch completeLatch = new CountDownLatch(1);
        client.newRequest("0.0.0.1", connector.getLocalPort())
                .scheme(getScheme())
                .content(content)
                .send(result ->
                {
                    Assert.assertTrue(result.isFailed());
                    completeLatch.countDown();
                });

        Assert.assertTrue(completeLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(closeLatch.await(5, TimeUnit.SECONDS));
    }
