    @Test
    public void testNeedClientAuthWithoutAuth() throws Exception
    {
        SslContextFactory serverSSL = new SslContextFactory();
        serverSSL.setKeyStorePath("src/test/resources/keystore.jks");
        serverSSL.setKeyStorePassword("storepwd");
        serverSSL.setNeedClientAuth(true);
        startServer(serverSSL, new EmptyServerHandler());

        SslContextFactory clientSSL = new SslContextFactory(true);
        startClient(clientSSL);
        CountDownLatch handshakeLatch = new CountDownLatch(1);
        client.addBean(new SslHandshakeListener()
        {
            @Override
            public void handshakeFailed(Event event, Throwable failure)
            {
                Assert.assertThat(failure, Matchers.instanceOf(SSLHandshakeException.class));
                handshakeLatch.countDown();
            }
        });

        CountDownLatch latch = new CountDownLatch(1);
        client.newRequest("https://localhost:" + connector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send(result ->
                {
                    if (result.isFailed())
                        latch.countDown();
                });

        Assert.assertTrue(handshakeLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
