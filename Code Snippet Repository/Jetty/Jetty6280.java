    @Test
    public void testDecoderWithProtocol() throws Exception
    {
        URI uri = baseServerUri.resolve("/timedecoder");

        try (BlockheadClient client = new BlockheadClient(uri))
        {
            client.addHeader("Sec-Websocket-Protocol: gmt\r\n");
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            client.write(new TextFrame().setPayload("2016-06-20T14:27:44"));
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1, TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            Assert.assertThat("Frame Response", frame.getPayloadAsUTF8(), is("cal=2016.06.20 AD at 14:27:44 +0000"));
        }
    }
