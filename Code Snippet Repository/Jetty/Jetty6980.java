    @Test(timeout=8000)
    public void testEchoTooBig() throws IOException, Exception
    {
        BlockheadClient client = new BlockheadClient(serverUri);
        try(StacklessLogging logging = new StacklessLogging(Parser.class))
        {
            client.setProtocols("echo");
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            // Generate text frame
            int size = 120 * 1024;
            byte buf[] = new byte[size]; // buffer bigger than maxMessageSize
            Arrays.fill(buf,(byte)'x');
            client.write(new TextFrame().setPayload(ByteBuffer.wrap(buf)));

            // Read frame (hopefully close frame saying its too large)
            EventQueue<WebSocketFrame> frames = client.readFrames(1,30,TimeUnit.SECONDS);
            WebSocketFrame tf = frames.poll();
            Assert.assertThat("Frame is close", tf.getOpCode(), is(OpCode.CLOSE));
            CloseInfo close = new CloseInfo(tf);
            Assert.assertThat("Close Code", close.getStatusCode(), is(StatusCode.MESSAGE_TOO_LARGE));
        }
        finally
        {
            client.close();
        }
    }
