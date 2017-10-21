    @Slow
    @Test
    public void testTimeoutOnListener() throws Exception
    {
        long timeout = 1000;
        start(new TimeoutHandler(2 * timeout));

        final CountDownLatch latch = new CountDownLatch(1);
        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(timeout, TimeUnit.MILLISECONDS);
        request.send(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                Assert.assertTrue(result.isFailed());
                latch.countDown();
            }
        });
        Assert.assertTrue(latch.await(3 * timeout, TimeUnit.MILLISECONDS));
    }
