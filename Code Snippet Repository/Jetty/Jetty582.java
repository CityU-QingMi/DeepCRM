    private void testConnectTimeoutFailsRequest(boolean blocking) throws Exception
    {
        String host = "10.255.255.1";
        int port = 80;
        int connectTimeout = 1000;
        assumeConnectTimeout(host, port, connectTimeout);

        start(new EmptyServerHandler());
        client.stop();
        client.setConnectTimeout(connectTimeout);
        client.setConnectBlocking(blocking);
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
                            latch.countDown();
                    }
                });

        Assert.assertTrue(latch.await(2 * connectTimeout, TimeUnit.MILLISECONDS));
        Assert.assertNotNull(request.getAbortCause());
    }
