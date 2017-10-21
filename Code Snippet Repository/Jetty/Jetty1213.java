    @Test
    public void testOneConcurrentStream() throws Exception
    {
        long sleep = 1000;
        start(1, new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                // Sleep a bit to allow the second request to be queued.
                sleep(sleep);
            }
        });
        client.setMaxConnectionsPerDestination(1);

        primeConnection();

        CountDownLatch latch = new CountDownLatch(2);

        // First request is sent immediately.
        client.newRequest("localhost", connector.getLocalPort())
                .path("/first")
                .send(result ->
                {
                    if (result.isSucceeded())
                        latch.countDown();
                });

        // Second request is queued.
        client.newRequest("localhost", connector.getLocalPort())
                .path("/second")
                .send(result ->
                {
                    if (result.isSucceeded())
                        latch.countDown();
                });

        // When the first request returns, the second must be sent.
        Assert.assertTrue(latch.await(5 * sleep, TimeUnit.MILLISECONDS));
    }
