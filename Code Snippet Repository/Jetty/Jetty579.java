    @Slow
    @Test
    public void testTimeoutOnListenerWithExplicitConnection() throws Exception
    {
        long timeout = 1000;
        start(new TimeoutHandler(2 * timeout));

        final CountDownLatch latch = new CountDownLatch(1);
        Destination destination = client.getDestination(scheme, "localhost", connector.getLocalPort());
        FuturePromise<Connection> futureConnection = new FuturePromise<>();
        destination.newConnection(futureConnection);
        try (Connection connection = futureConnection.get(5, TimeUnit.SECONDS))
        {
            Request request = client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .timeout(timeout, TimeUnit.MILLISECONDS);
            connection.send(request, new Response.CompleteListener()
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
    }
