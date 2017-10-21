    @Test
    public void retryAfterConnectTimeout() throws Exception
    {
        final String host = "10.255.255.1";
        final int port = 80;
        int connectTimeout = 1000;
        assumeConnectTimeout(host, port, connectTimeout);

        start(new EmptyServerHandler());
        client.stop();
        client.setConnectTimeout(connectTimeout);
        client.start();

        final CountDownLatch latch = new CountDownLatch(1);
        Request request = client.newRequest(host, port);
        request.send(result1 ->
        {
            if (result1.isFailed())
            {
                // Retry
                client.newRequest(host, port).send(result2 ->
                {
                    if (result2.isFailed())
                        latch.countDown();
                });
            }
        });

        Assert.assertTrue(latch.await(3 * connectTimeout, TimeUnit.MILLISECONDS));
        Assert.assertNotNull(request.getAbortCause());
    }
