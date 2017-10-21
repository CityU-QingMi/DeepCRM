    @Slow
    @Test
    public void testConnectTimeoutIsCancelledByShorterRequestTimeout() throws Exception
    {
        String host = "10.255.255.1";
        int port = 80;
        int connectTimeout = 2000;
        assumeConnectTimeout(host, port, connectTimeout);

        start(new EmptyServerHandler());
        client.stop();
        client.setConnectTimeout(connectTimeout);
        client.start();

        final AtomicInteger completes = new AtomicInteger();
        final CountDownLatch latch = new CountDownLatch(2);
        Request request = client.newRequest(host, port);
        request.scheme(scheme)
                .timeout(connectTimeout / 2, TimeUnit.MILLISECONDS)
                .send(new Response.CompleteListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        completes.incrementAndGet();
                        latch.countDown();
                    }
                });

        Assert.assertFalse(latch.await(2 * connectTimeout, TimeUnit.MILLISECONDS));
        Assert.assertEquals(1, completes.get());
        Assert.assertNotNull(request.getAbortCause());
    }
