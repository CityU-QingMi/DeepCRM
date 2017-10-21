    @Test
    public void testOpenSessionCleanup() throws Exception
    {
        fastFail();
        fastClose();
        dropConnection();

        try (IBlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setProtocols("container");
            client.setTimeout(1,TimeUnit.SECONDS);
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            TextFrame text = new TextFrame();
            text.setPayload("openSessions");
            client.write(text);

            EventQueue<WebSocketFrame> frames = client.readFrames(2,1,TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            assertThat("frames[0].opcode",frame.getOpCode(),is(OpCode.TEXT));

            String resp = frame.getPayloadAsUTF8();
            assertThat("Should only have 1 open session",resp,containsString("openSessions.size=1\n"));

            frame = frames.poll();
            assertThat("frames[1].opcode",frame.getOpCode(),is(OpCode.CLOSE));
            CloseInfo close = new CloseInfo(frame);
            assertThat("Close Status Code",close.getStatusCode(),is(StatusCode.NORMAL));
            client.write(close.asFrame()); // respond with close

            // ensure server socket got close event
            assertThat("Open Sessions Latch",closeSocket.closeLatch.await(1,TimeUnit.SECONDS),is(true));
            assertThat("Open Sessions.statusCode",closeSocket.closeStatusCode,is(StatusCode.NORMAL));
            assertThat("Open Sessions.errors",closeSocket.errors.size(),is(0));
        }
    }
