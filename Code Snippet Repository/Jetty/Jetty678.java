    @Test
    public void testWantClientAuthWithAuth() throws Exception
    {
        SslContextFactory serverSSL = new SslContextFactory();
        serverSSL.setKeyStorePath("src/test/resources/keystore.jks");
        serverSSL.setKeyStorePassword("storepwd");
        serverSSL.setWantClientAuth(true);
        startServer(serverSSL, new EmptyServerHandler());
        CountDownLatch handshakeLatch = new CountDownLatch(1);
        connector.addBean(new SslHandshakeListener()
        {
            @Override
            public void handshakeSucceeded(Event event)
            {
                try
                {
                    SSLSession session = event.getSSLEngine().getSession();
                    Certificate[] clientCerts = session.getPeerCertificates();
                    Assert.assertNotNull(clientCerts);
                    Assert.assertThat(clientCerts.length, Matchers.greaterThan(0));
                    handshakeLatch.countDown();
                }
                catch (Throwable x)
                {
                    x.printStackTrace();
                }
            }
        });

        SslContextFactory clientSSL = new SslContextFactory(true);
        clientSSL.setKeyStorePath("src/test/resources/client_keystore.jks");
        clientSSL.setKeyStorePassword("storepwd");
        startClient(clientSSL);

        ContentResponse response = client.newRequest("https://localhost:" + connector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertTrue(handshakeLatch.await(5, TimeUnit.SECONDS));
    }
