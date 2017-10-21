    @Test
    public void testListenerRuntimeOnConnect() throws Exception
    {
        try (IBlockheadClient client = new BlockheadClient(server.getServerUri());
             StacklessLogging scope = new StacklessLogging(ListenerRuntimeOnConnectSocket.class, WebSocketSession.class))
        {
            client.setProtocols("listener-runtime-connect");
            client.setTimeout(1,TimeUnit.SECONDS);

            ListenerRuntimeOnConnectSocket socket = badSocketsServlet.listenerRuntimeConnect;
            socket.reset();

            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            EventQueue<WebSocketFrame> frames = client.readFrames(1,1,TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            assertThat("frames[0].opcode",frame.getOpCode(),is(OpCode.CLOSE));
            CloseInfo close = new CloseInfo(frame);
            assertThat("Close Status Code",close.getStatusCode(),is(StatusCode.SERVER_ERROR));

            client.write(close.asFrame()); // respond with close

            // ensure server socket got close event
            assertThat("Close Latch",socket.closeLatch.await(1,TimeUnit.SECONDS),is(true));
            assertThat("closeStatusCode",socket.closeStatusCode,is(StatusCode.SERVER_ERROR));

            // Validate errors
            assertThat("socket.onErrors",socket.errors.size(),is(1));
            Throwable cause = socket.errors.pop();
            assertThat("Error type",cause,instanceOf(RuntimeException.class));
        }
    }
