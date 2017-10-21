    @Test
    public void testAbortOnBegin() throws Exception
    {
        start(new EmptyServerHandler());

        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onResponseBegin(response -> response.abort(new Exception()))
                .send(result ->
                {
                    Assert.assertTrue(result.isFailed());
                    latch.countDown();
                });
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
