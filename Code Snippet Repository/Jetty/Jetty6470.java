    @Test
    @Slow
    public void testServerSlowToSend() throws Exception
    {
        JettyTrackingSocket clientSocket = new JettyTrackingSocket();
        client.setMasker(new ZeroMasker());
        client.setMaxIdleTimeout(60000);

        URI wsUri = server.getWsUri();
        Future<Session> clientConnectFuture = client.connect(clientSocket,wsUri);

        IBlockheadServerConnection serverConn = server.accept();
        serverConn.setSoTimeout(60000);
        serverConn.upgrade();

        // Confirm connected
        clientConnectFuture.get(30,TimeUnit.SECONDS);
        clientSocket.waitForConnected(30,TimeUnit.SECONDS);

        // Have server write slowly.
        int messageCount = 1000;

        ServerWriteThread writer = new ServerWriteThread(serverConn);
        writer.setMessageCount(messageCount);
        writer.setMessage("Hello");
        writer.setSlowness(10);
        writer.start();
        writer.join();

        // Verify receive
        Assert.assertThat("Message Receive Count",clientSocket.messageQueue.size(),is(messageCount));

        // Close
        serverConn.close(StatusCode.NORMAL);

        Assert.assertTrue("Client Socket Closed",clientSocket.closeLatch.await(10,TimeUnit.SECONDS));
        clientSocket.assertCloseCode(StatusCode.NORMAL);
    }
