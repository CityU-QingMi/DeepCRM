    @Test
    public void testConnectThrowsUnknownHostException() throws Exception
    {
        String host = "idontexist";
        int port = 80;

        try
        {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 1000);
            Assume.assumeTrue("Host must not be resolvable", false);
        }
        catch (IOException ignored)
        {
        }

        start(new EmptyServerHandler());

        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(host, port)
                .send(result ->
                {
                    Assert.assertTrue(result.isFailed());
                    Throwable failure = result.getFailure();
                    Assert.assertTrue(failure instanceof UnknownHostException);
                    latch.countDown();
                });
        Assert.assertTrue(latch.await(10, TimeUnit.SECONDS));
    }
