    @Test
    public void testNoExtensionsConfigurator() throws Exception
    {
        URI uri = baseServerUri.resolve("/no-extensions");

        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.addExtensions("identity");
            client.connect();
            client.sendStandardRequest();
            HttpResponse response = client.expectUpgradeResponse();
            assertThat("response.extensions", response.getExtensionsHeader(), nullValue());
    
            client.write(new TextFrame().setPayload("NegoExts"));
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1, TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            assertThat("Frame Response", frame.getPayloadAsUTF8(), is("negotiatedExtensions=[]"));
        }
    }
