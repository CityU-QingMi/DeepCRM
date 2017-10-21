    private void confirmConnection(CloseTrackingSocket clientSocket, Future<Session> clientFuture, IBlockheadServerConnection serverConns) throws Exception
    {
        // Wait for client connect on via future
        clientFuture.get(30,TimeUnit.SECONDS);

        // Wait for client connect via client websocket
        assertThat("Client WebSocket is Open",clientSocket.openLatch.await(30,TimeUnit.SECONDS),is(true));

        try
        {
            // Send message from client to server
            final String echoMsg = "echo-test";
            Future<Void> testFut = clientSocket.getRemote().sendStringByFuture(echoMsg);

            // Wait for send future
            testFut.get(30,TimeUnit.SECONDS);

            // Read Frame on server side
            IncomingFramesCapture serverCapture = serverConns.readFrames(1,30,TimeUnit.SECONDS);
            serverCapture.assertNoErrors();
            serverCapture.assertFrameCount(1);
            WebSocketFrame frame = serverCapture.getFrames().poll();
            assertThat("Server received frame",frame.getOpCode(),is(OpCode.TEXT));
            assertThat("Server received frame payload",frame.getPayloadAsUTF8(),is(echoMsg));

            // Server send echo reply
            serverConns.write(new TextFrame().setPayload(echoMsg));

            // Wait for received echo
            clientSocket.messageQueue.awaitEventCount(1,1,TimeUnit.SECONDS);

            // Verify received message
            String recvMsg = clientSocket.messageQueue.poll();
            assertThat("Received message",recvMsg,is(echoMsg));

            // Verify that there are no errors
            assertThat("Error events",clientSocket.error.get(),nullValue());
        }
        finally
        {
            clientSocket.clearQueues();
        }
    }
