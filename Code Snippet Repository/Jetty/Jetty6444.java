    @Ignore("")
    @Test
    public void testNetworkCongestion() throws Exception
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

        // client sends BIG frames (until it cannot write anymore)
        // server must not read (for test purpose, in order to congest connection)
        // when write is congested, client enqueue close frame
        // client initiate write, but write never completes
        EndPoint endp = clientSocket.getEndPoint();
        assertThat("EndPoint is testable",endp,instanceOf(TestEndPoint.class));
        TestEndPoint testendp = (TestEndPoint)endp;

        char msg[] = new char[10240];
        int writeCount = 0;
        long writeSize = 0;
        int i = 0;
        while (!testendp.congestedFlush.get())
        {
            int z = i - ((i / 26) * 26);
            char c = (char)('a' + z);
            Arrays.fill(msg,c);
            clientSocket.getRemote().sendStringByFuture(String.valueOf(msg));
            writeCount++;
            writeSize += msg.length;
        }
        LOG.info("Wrote {} frames totalling {} bytes of payload before congestion kicked in",writeCount,writeSize);

        // Verify timeout error
        assertThat("OnError Latch", clientSocket.errorLatch.await(2, TimeUnit.SECONDS), is(true));
        assertThat("OnError", clientSocket.error.get(), instanceOf(SocketTimeoutException.class));
    }
