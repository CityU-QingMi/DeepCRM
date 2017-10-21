    @Test
    public void testAbortOnHeaders() throws Exception
    {
        start(new EmptyServerHandler());

        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onResponseHeaders(response -> response.abort(new Exception()))
                .send(result ->
                {
                    Assert.assertTrue(result.isFailed());
                    latch.countDown();
                });
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
