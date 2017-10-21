    @Test
    public void testWriteException() throws Exception
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

        // setup client endpoint for write failure (test only)
        EndPoint endp = clientSocket.getEndPoint();
        endp.shutdownOutput();

        // client enqueue close frame
        // client write failure
        final String origCloseReason = "Normal Close";
        clientSocket.getSession().close(StatusCode.NORMAL,origCloseReason);
    
        assertThat("OnError Latch", clientSocket.errorLatch.await(2, TimeUnit.SECONDS), is(true));
        assertThat("OnError", clientSocket.error.get(), instanceOf(EofException.class));

        // client triggers close event on client ws-endpoint
        // assert - close code==1006 (abnormal)
        // assert - close reason message contains (write failure)
        clientSocket.assertReceivedCloseEvent(timeout,is(StatusCode.ABNORMAL),containsString("EOF"));
    }
