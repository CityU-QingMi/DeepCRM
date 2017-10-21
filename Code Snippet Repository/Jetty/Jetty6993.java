    @Test
    public void testIdleTimeout() throws Exception
    {
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        client.setProtocols("onConnect");
        client.setTimeout(2500,TimeUnit.MILLISECONDS);
        try
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            // This wait should be shorter than client timeout above, but
            // longer than server timeout configured in TimeoutServlet
            client.sleep(TimeUnit.MILLISECONDS,1000);

            // Write to server
            // This action is possible, but does nothing.
            // Server could be in a half-closed state at this point.
            // Where the server read is closed (due to timeout), but the server write is still open.
            // The server could not read this frame, if it is in this half closed state
            client.write(new TextFrame().setPayload("Hello"));

            // Expect server to have closed due to its own timeout
            EventQueue<WebSocketFrame> frames = client.readFrames(1,30,TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            Assert.assertThat("frame opcode",frame.getOpCode(),is(OpCode.CLOSE));
            CloseInfo close = new CloseInfo(frame);
            Assert.assertThat("close code",close.getStatusCode(),is(StatusCode.SHUTDOWN));
            Assert.assertThat("close reason",close.getReason(),containsString("Timeout"));
        }
        finally
        {
            client.close();
        }
    }
