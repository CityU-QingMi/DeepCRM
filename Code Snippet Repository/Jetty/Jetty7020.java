    @Test
    public void testFastClose() throws Exception
    {
        try (IBlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setProtocols("fastclose");
            client.setTimeout(5,TimeUnit.SECONDS);
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            // Verify that client got close frame
            EventQueue<WebSocketFrame> frames = client.readFrames(1,5,TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            assertThat("frames[0].opcode",frame.getOpCode(),is(OpCode.CLOSE));
            CloseInfo close = new CloseInfo(frame);
            assertThat("Close Status Code",close.getStatusCode(),is(StatusCode.NORMAL));

            // Notify server of close handshake
            client.write(close.asFrame()); // respond with close

            // ensure server socket got close event
            assertThat("Fast Close Latch",closeSocket.closeLatch.await(5,TimeUnit.SECONDS),is(true));
            assertThat("Fast Close.statusCode",closeSocket.closeStatusCode,is(StatusCode.NORMAL));
        }
    }
