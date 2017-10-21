    @Test
    public void testIdleTimeoutJustBeforeAssociation() throws Exception
    {
        startServer(new EmptyServerHandler());

        long idleTimeout = 1000;
        client = new HttpClient(newHttpClientTransport(transport, exchange ->
        {
            // We idle timeout just before the association,
            // we must be able to send the request successfully.
            sleep(2 * idleTimeout);
            return true;
        }), sslContextFactory);
        QueuedThreadPool clientThreads = new QueuedThreadPool();
        clientThreads.setName("client");
        client.setExecutor(clientThreads);
        client.setIdleTimeout(idleTimeout);
        client.start();

        CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(newURI())
                .send(result ->
                {
                    if (result.isSucceeded())
                        latch.countDown();
                });

        Assert.assertTrue(latch.await(5 * idleTimeout, TimeUnit.MILLISECONDS));
    }
