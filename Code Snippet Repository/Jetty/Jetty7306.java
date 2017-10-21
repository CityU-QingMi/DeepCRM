    @Test
    public void testEmptyOutputStream() throws Exception
    {
        start(new ConsumeInputHandler());

        OutputStreamContentProvider contentProvider = new OutputStreamContentProvider();
        CountDownLatch latch = new CountDownLatch(1);
        client.POST(newURI())
                .content(contentProvider)
                .send(result ->
                {
                    if (result.isSucceeded() &&
                            result.getResponse().getStatus() == HttpStatus.OK_200)
                        latch.countDown();
                });
        contentProvider.close();

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
