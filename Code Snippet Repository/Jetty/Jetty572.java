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
        request.scheme(scheme)
                .send(new Response.CompleteListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        if (result.isFailed())
                        {
                            // Retry
                            client.newRequest(host, port)
                                    .scheme(scheme)
                                    .send(new Response.CompleteListener()
                                    {
                                        @Override
                                        public void onComplete(Result result)
                                        {
                                            if (result.isFailed())
                                                latch.countDown();
                                        }
                                    });
                        }
                    }
                });

        Assert.assertTrue(latch.await(333 * connectTimeout, TimeUnit.MILLISECONDS));
        Assert.assertNotNull(request.getAbortCause());
    }
