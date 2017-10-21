    @Test
    public void testAbortOnHeader() throws Exception
    {
        start(new EmptyServerHandler());

        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onResponseHeader((response, field) ->
                {
                    response.abort(new Exception());
                    return true;
                })
                .send(result ->
                {
                    Assert.assertTrue(result.isFailed());
                    latch.countDown();
                });
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
