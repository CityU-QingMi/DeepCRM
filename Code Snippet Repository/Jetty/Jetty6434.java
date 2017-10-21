    @Test
    public void testReadEOF() throws Exception
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

        // server shuts down connection (no frame reply)
        serverConn.disconnect();

        // client reads -1 (EOF)
        // client triggers close event on client ws-endpoint
        clientSocket.assertReceivedCloseEvent(timeout,is(StatusCode.ABNORMAL),containsString("EOF"));
    }
