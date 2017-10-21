    @Test
    public void testServerNoCloseHandshake() throws Exception
    {
        // Set client timeout
        final int timeout = 1000;
        client.setMaxIdleTimeout(timeout);

        // Client connects
        CloseTrackingSocket clientSocket = new CloseTrackingSocket();
        Future<Session> clientConnectFuture = client.connect(clientSocket,server.getWsUri());

        // Server accepts connect
        IBlockheadServerConnection serverConn = server.accept();
        serverConn.upgrade();

        // client confirms connection via echo
        confirmConnection(clientSocket,clientConnectFuture,serverConn);

        // client sends close frame
        final String origCloseReason = "Normal Close";
        clientSocket.getSession().close(StatusCode.NORMAL,origCloseReason);

        // server receives close frame
        confirmServerReceivedCloseFrame(serverConn,StatusCode.NORMAL,is(origCloseReason));

        // client should not have received close message (yet)
        clientSocket.assertNoCloseEvent();

        // server never sends close frame handshake
        // server sits idle

        // client idle timeout triggers close event on client ws-endpoint
        assertThat("OnError Latch", clientSocket.errorLatch.await(2, TimeUnit.SECONDS), is(true));
        assertThat("OnError", clientSocket.error.get(), instanceOf(SocketTimeoutException.class));
        assertThat("OnError", clientSocket.error.get().getMessage(), containsString("Timeout on Read"));
    }
