    @Slow
    @Test
    public void testTimeoutOnQueuedRequest() throws Exception
    {
        long timeout = 1000;
        start(new TimeoutHandler(3 * timeout));

        // Only one connection so requests get queued
        client.setMaxConnectionsPerDestination(1);

        // The first request has a long timeout
        final CountDownLatch firstLatch = new CountDownLatch(1);
        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(4 * timeout, TimeUnit.MILLISECONDS);
        request.send(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                Assert.assertFalse(result.isFailed());
                firstLatch.countDown();
            }
        });

        // Second request has a short timeout and should fail in the queue
        final CountDownLatch secondLatch = new CountDownLatch(1);
        request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(timeout, TimeUnit.MILLISECONDS);
        request.send(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                Assert.assertTrue(result.isFailed());
                secondLatch.countDown();
            }
        });

        Assert.assertTrue(secondLatch.await(2 * timeout, TimeUnit.MILLISECONDS));
        // The second request must fail before the first request has completed
        Assert.assertTrue(firstLatch.getCount() > 0);
        Assert.assertTrue(firstLatch.await(5 * timeout, TimeUnit.MILLISECONDS));
    }
