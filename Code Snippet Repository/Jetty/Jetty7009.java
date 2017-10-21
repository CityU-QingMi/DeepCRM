    @Test
    public void testSuspendResume() throws Exception
    {
        try (BlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setTimeout(1, TimeUnit.SECONDS);
            
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();
            
            client.write(new TextFrame().setPayload("echo1"));
            client.write(new TextFrame().setPayload("echo2"));
            
            EventQueue<WebSocketFrame> frames = client.readFrames(2, 30, TimeUnit.SECONDS);
            WebSocketFrame tf = frames.poll();
            assertThat(EchoSocket.class.getSimpleName() + ".onMessage()", tf.getPayloadAsUTF8(), is("echo1"));
            tf = frames.poll();
            assertThat(EchoSocket.class.getSimpleName() + ".onMessage()", tf.getPayloadAsUTF8(), is("echo2"));
        }
    }
