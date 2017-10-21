    @Test
    public void testDeferredContent() throws Exception
    {
        start(new ConsumeInputHandler());

        DeferredContentProvider contentProvider = new DeferredContentProvider();
        CountDownLatch latch = new CountDownLatch(1);
        client.POST(newURI())
                .content(contentProvider)
                .send(result ->
                {
                    if (result.isSucceeded() &&
                            result.getResponse().getStatus() == HttpStatus.OK_200)
                        latch.countDown();
                });
        contentProvider.offer(ByteBuffer.wrap(new byte[1]));
        contentProvider.close();

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
