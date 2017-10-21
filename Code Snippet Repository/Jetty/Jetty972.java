    @Test
    public void testConnectTimeout() throws Exception
    {
        final String host = "10.255.255.1";
        final int port = 80;
        int connectTimeout = 1000;
        assumeConnectTimeout(host, port, connectTimeout);

        start(new ServerSessionListener.Adapter());
        client.setConnectTimeout(connectTimeout);

        InetSocketAddress address = new InetSocketAddress(host, port);
        final CountDownLatch latch = new CountDownLatch(1);
        client.connect(address, new Session.Listener.Adapter(), new Promise.Adapter<Session>()
        {
            @Override
            public void failed(Throwable x)
            {
                Assert.assertTrue(x instanceof SocketTimeoutException);
                latch.countDown();
            }
        });

        Assert.assertTrue(latch.await(2 * connectTimeout, TimeUnit.MILLISECONDS));
    }
