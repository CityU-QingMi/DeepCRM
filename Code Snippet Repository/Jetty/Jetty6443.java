    @Test
    public void testHalfClose() throws Exception
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

        // client sends close frame (code 1000, normal)
        final String origCloseReason = "Normal Close";
        clientSocket.getSession().close(StatusCode.NORMAL,origCloseReason);

        // server receives close frame
        confirmServerReceivedCloseFrame(serverConn,StatusCode.NORMAL,is(origCloseReason));

        // server sends 2 messages
        serverConn.write(new TextFrame().setPayload("Hello"));
        serverConn.write(new TextFrame().setPayload("World"));

        // server sends close frame (code 1000, no reason)
        CloseInfo sclose = new CloseInfo(StatusCode.NORMAL,"From Server");
        serverConn.write(sclose.asFrame());

        // client receives 2 messages
        clientSocket.messageQueue.awaitEventCount(2,1,TimeUnit.SECONDS);

        // Verify received messages
        String recvMsg = clientSocket.messageQueue.poll();
        assertThat("Received message 1",recvMsg,is("Hello"));
        recvMsg = clientSocket.messageQueue.poll();
        assertThat("Received message 2",recvMsg,is("World"));

        // Verify that there are no errors
        assertThat("Error events",clientSocket.error.get(),nullValue());

        // client close event on ws-endpoint
        clientSocket.assertReceivedCloseEvent(timeout,is(StatusCode.NORMAL),containsString("From Server"));
    }
