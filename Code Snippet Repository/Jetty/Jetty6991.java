    @Test
    public void testFragmentExtension() throws Exception
    {
        int fragSize = 4;

        BlockheadClient client = new BlockheadClient(server.getServerUri());
        client.clearExtensions();
        client.addExtensions("fragment;maxLength=" + fragSize);
        client.setProtocols("onConnect");

        try
        {
            // Make sure the read times out if there are problems with the implementation
            client.setTimeout(1,TimeUnit.SECONDS);
            client.connect();
            client.sendStandardRequest();
            HttpResponse resp = client.expectUpgradeResponse();

            Assert.assertThat("Response",resp.getExtensionsHeader(),containsString("fragment"));

            String msg = "Sent as a long message that should be split";
            client.write(new TextFrame().setPayload(msg));

            String parts[] = split(msg,fragSize);
            EventQueue<WebSocketFrame> frames = client.readFrames(parts.length,1000,TimeUnit.MILLISECONDS);
            for (int i = 0; i < parts.length; i++)
            {
                WebSocketFrame frame = frames.poll();
                Assert.assertThat("text[" + i + "].payload",frame.getPayloadAsUTF8(),is(parts[i]));
            }
        }
        finally
        {
            client.close();
        }
    }
