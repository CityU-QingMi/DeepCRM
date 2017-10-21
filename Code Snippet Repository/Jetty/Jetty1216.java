    @Test
    public void testMultipleRequestsQueuedOnConnect() throws Exception
    {
        int maxConcurrent = 10;
        long sleep = 500;
        start(maxConcurrent, new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                sleep(sleep);
            }
        });
        client.setMaxConnectionsPerDestination(1);

        // The first request will open the connection, the others will be queued.
        CountDownLatch latch = new CountDownLatch(maxConcurrent);
        for (int i = 0; i < maxConcurrent; ++i)
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .path("/" + i)
                    .send(result -> latch.countDown());
        }

        // The requests should be processed in parallel, not sequentially.
        Assert.assertTrue(latch.await(maxConcurrent * sleep / 2, TimeUnit.MILLISECONDS));
    }
