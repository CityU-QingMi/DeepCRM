    private void confirmServerReceivedCloseFrame(IBlockheadServerConnection serverConn, int expectedCloseCode, Matcher<String> closeReasonMatcher) throws IOException,
            TimeoutException
    {
        IncomingFramesCapture serverCapture = serverConn.readFrames(1,30,TimeUnit.SECONDS);
        serverCapture.assertNoErrors();
        serverCapture.assertFrameCount(1);
        serverCapture.assertHasFrame(OpCode.CLOSE,1);
        WebSocketFrame frame = serverCapture.getFrames().poll();
        assertThat("Server received close frame",frame.getOpCode(),is(OpCode.CLOSE));
        CloseInfo closeInfo = new CloseInfo(frame);
        assertThat("Server received close code",closeInfo.getStatusCode(),is(expectedCloseCode));
        if (closeReasonMatcher == null)
        {
            assertThat("Server received close reason",closeInfo.getReason(),nullValue());
        }
        else
        {
            assertThat("Server received close reason",closeInfo.getReason(),closeReasonMatcher);
        }
    }
