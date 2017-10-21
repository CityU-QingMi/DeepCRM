    @Test
    public void testAssociationFailedAbortsRequest() throws Exception
    {
        startServer(new EmptyServerHandler());

        client = new HttpClient(newHttpClientTransport(transport, exchange -> false), sslContextFactory);
        QueuedThreadPool clientThreads = new QueuedThreadPool();
        clientThreads.setName("client");
        client.setExecutor(clientThreads);
        client.start();

        CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(newURI())
                .send(result ->
                {
                    if (result.isFailed())
                        latch.countDown();
                });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
