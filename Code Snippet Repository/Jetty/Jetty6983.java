    @Test
    public void testUpgradeWithWebkitDeflateExtension() throws Exception
    {
        Assume.assumeTrue("Server has x-webkit-deflate-frame registered",
                server.getWebSocketServletFactory().getExtensionFactory().isAvailable("x-webkit-deflate-frame"));
        
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        try
        {
            client.addExtensions("x-webkit-deflate-frame");
            client.setProtocols("chat");
            client.connect();
            client.sendStandardRequest();
            HttpResponse response = client.expectUpgradeResponse();
            Assert.assertThat("Response",response.getExtensionsHeader(),containsString("x-webkit-deflate-frame"));

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
