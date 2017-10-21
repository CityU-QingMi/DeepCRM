    @Test
    public void testClientRawCloseDoesNotInvalidateSession() throws Exception
    {
        SslContextFactory serverTLSFactory = createSslContextFactory();
        startServer(serverTLSFactory, new EmptyServerHandler());

        SslContextFactory clientTLSFactory = createSslContextFactory();
        clientTLSFactory.start();

        String host = "localhost";
        int port = connector.getLocalPort();
        Socket socket = new Socket(host, port);
        SSLSocket sslSocket = (SSLSocket)clientTLSFactory.getSslContext().getSocketFactory().createSocket(socket, host, port, true);
        CountDownLatch handshakeLatch1 = new CountDownLatch(1);
        AtomicReference<byte[]> session1 = new AtomicReference<>();
        sslSocket.addHandshakeCompletedListener(event ->
        {
            session1.set(event.getSession().getId());
            handshakeLatch1.countDown();
        });
        sslSocket.startHandshake();
        Assert.assertTrue(handshakeLatch1.await(5, TimeUnit.SECONDS));

        // The client closes abruptly.
        socket.close();

        // Try again and compare the session ids.
        socket = new Socket(host, port);
        sslSocket = (SSLSocket)clientTLSFactory.getSslContext().getSocketFactory().createSocket(socket, host, port, true);
        CountDownLatch handshakeLatch2 = new CountDownLatch(1);
        AtomicReference<byte[]> session2 = new AtomicReference<>();
        sslSocket.addHandshakeCompletedListener(event ->
        {
            session2.set(event.getSession().getId());
            handshakeLatch2.countDown();
        });
        sslSocket.startHandshake();
        Assert.assertTrue(handshakeLatch2.await(5, TimeUnit.SECONDS));

        Assert.assertArrayEquals(session1.get(), session2.get());

        sslSocket.close();
    }
