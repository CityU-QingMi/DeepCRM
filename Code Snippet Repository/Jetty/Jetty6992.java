    @Test
    public void testIdentityExtension() throws Exception
    {
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        client.clearExtensions();
        client.addExtensions("identity;param=0");
        client.addExtensions("identity;param=1, identity ; param = '2' ; other = ' some = value '");
        client.setProtocols("onConnect");

        try
        {
            // Make sure the read times out if there are problems with the implementation
            client.setTimeout(1,TimeUnit.SECONDS);
            client.connect();
            client.sendStandardRequest();
            HttpResponse resp = client.expectUpgradeResponse();

            Assert.assertThat("Response",resp.getExtensionsHeader(),containsString("identity"));

            client.write(new TextFrame().setPayload("Hello"));

            EventQueue<WebSocketFrame> frames = client.readFrames(1,1000,TimeUnit.MILLISECONDS);
            WebSocketFrame frame = frames.poll();
            Assert.assertThat("TEXT.payload",frame.getPayloadAsUTF8(),is("Hello"));
        }
        finally
        {
            client.close();
        }
    }
