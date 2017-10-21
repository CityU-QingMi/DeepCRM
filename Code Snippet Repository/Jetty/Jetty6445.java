    @Test
    public void testProtocolException() throws Exception
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

        // client should not have received close message (yet)
        clientSocket.assertNoCloseEvent();

        // server sends bad close frame (too big of a reason message)
        byte msg[] = new byte[400];
        Arrays.fill(msg,(byte)'x');
        ByteBuffer bad = ByteBuffer.allocate(500);
        RawFrameBuilder.putOpFin(bad,OpCode.CLOSE,true);
        RawFrameBuilder.putLength(bad,msg.length + 2,false);
        bad.putShort((short)StatusCode.NORMAL);
        bad.put(msg);
        BufferUtil.flipToFlush(bad,0);
        try (StacklessLogging quiet = new StacklessLogging(Parser.class))
        {
            serverConn.write(bad);

            // client should have noticed the error
            assertThat("OnError Latch", clientSocket.errorLatch.await(2, TimeUnit.SECONDS), is(true));
            assertThat("OnError", clientSocket.error.get(), instanceOf(ProtocolException.class));
            assertThat("OnError", clientSocket.error.get().getMessage(), containsString("Invalid control frame"));

            // client parse invalid frame, notifies server of close (protocol error)
            confirmServerReceivedCloseFrame(serverConn,StatusCode.PROTOCOL,allOf(containsString("Invalid control frame"),containsString("length")));
        }

        // server disconnects
        serverConn.disconnect();

        // client triggers close event on client ws-endpoint
        clientSocket.assertReceivedCloseEvent(timeout,is(StatusCode.PROTOCOL),allOf(containsString("Invalid control frame"),containsString("length")));
    }
