    @Test
    public void testFastFail() throws Exception
    {
        try (IBlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setProtocols("fastfail");
            client.setTimeout(5,TimeUnit.SECONDS);
            try (StacklessLogging scope = new StacklessLogging(FastFailSocket.class, WebSocketSession.class))
            {
                client.connect();
                client.sendStandardRequest();
                client.expectUpgradeResponse();

                EventQueue<WebSocketFrame> frames = client.readFrames(1,5,TimeUnit.SECONDS);
                WebSocketFrame frame = frames.poll();
                assertThat("frames[0].opcode",frame.getOpCode(),is(OpCode.CLOSE));
                CloseInfo close = new CloseInfo(frame);
                assertThat("Close Status Code",close.getStatusCode(),is(StatusCode.SERVER_ERROR));

                client.write(close.asFrame()); // respond with close

                // ensure server socket got close event
                assertThat("Fast Fail Latch",closeSocket.closeLatch.await(5,TimeUnit.SECONDS),is(true));
                assertThat("Fast Fail.statusCode",closeSocket.closeStatusCode,is(StatusCode.SERVER_ERROR));
                assertThat("Fast Fail.errors",closeSocket.errors.size(),is(1));
            }
        }
    }
