    @Test
    public void testEchoGood() throws IOException, Exception
    {
        BlockheadClient client = new BlockheadClient(serverUri);
        try
        {
            client.setProtocols("echo");
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            // Generate text frame
            String msg = "this is an echo ... cho ... ho ... o";
            client.write(new TextFrame().setPayload(msg));

            // Read frame (hopefully text frame)
            EventQueue<WebSocketFrame> frames = client.readFrames(1,30,TimeUnit.SECONDS);
            WebSocketFrame tf = frames.poll();
            Assert.assertThat("Text Frame.status code",tf.getPayloadAsUTF8(),is(msg));
        }
        finally
        {
            client.close();
        }
    }
