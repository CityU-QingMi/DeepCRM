    @Test
    public void testTwoConcurrentStreamsThirdWaits() throws Exception
    {
        int maxStreams = 2;
        long sleep = 1000;
        start(maxStreams, new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                sleep(sleep);
            }
        });
        client.setMaxConnectionsPerDestination(1);

        primeConnection();

        // Send requests up to the max allowed.
        for (int i = 0; i < maxStreams; ++i)
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .path("/" + i)
                    .send(null);
        }

        // Send the request in excess.
        CountDownLatch latch = new CountDownLatch(1);
        String path = "/excess";
        client.newRequest("localhost", connector.getLocalPort())
                .path(path)
                .send(result ->
                {
                    if (result.getResponse().getStatus() == HttpStatus.OK_200)
                        latch.countDown();
                });

        // The last exchange should remain in the queue.
        HttpDestinationOverHTTP2 destination = (HttpDestinationOverHTTP2)client.getDestination("http", "localhost", connector.getLocalPort());
        Assert.assertEquals(1, destination.getHttpExchanges().size());
        Assert.assertEquals(path, destination.getHttpExchanges().peek().getRequest().getPath());

        Assert.assertTrue(latch.await(5 * sleep, TimeUnit.MILLISECONDS));
    }
