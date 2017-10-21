    @Slow
    @Test
    public void testTimeoutIsCancelledOnSuccessWithExplicitConnection() throws Exception
    {
        long timeout = 1000;
        start(new TimeoutHandler(timeout));

        final CountDownLatch latch = new CountDownLatch(1);
        Destination destination = client.getDestination(scheme, "localhost", connector.getLocalPort());
        FuturePromise<Connection> futureConnection = new FuturePromise<>();
        destination.newConnection(futureConnection);
        try (Connection connection = futureConnection.get(5, TimeUnit.SECONDS))
        {
            Request request = client.newRequest(destination.getHost(), destination.getPort())
                    .scheme(scheme)
                    .timeout(2 * timeout, TimeUnit.MILLISECONDS);
            connection.send(request, new Response.CompleteListener()
            {
                @Override
                public void onComplete(Result result)
                {
                    Response response = result.getResponse();
                    Assert.assertEquals(200, response.getStatus());
                    Assert.assertFalse(result.isFailed());
                    latch.countDown();
                }
            });

            Assert.assertTrue(latch.await(3 * timeout, TimeUnit.MILLISECONDS));

            TimeUnit.MILLISECONDS.sleep(2 * timeout);

            Assert.assertNull(request.getAbortCause());
        }
    }
