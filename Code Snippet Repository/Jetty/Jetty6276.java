    @Test
    public void testProtocol_Single() throws Exception
    {
        URI uri = baseServerUri.resolve("/protocols");
        ProtocolsConfigurator.seenProtocols.set(null);

        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.addHeader("Sec-WebSocket-Protocol: echo\r\n");
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            client.write(new TextFrame().setPayload("getProtocols"));
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1, TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            Assert.assertThat("Frame Response", frame.getPayloadAsUTF8(), is("Requested Protocols: [\"echo\"]"));
        }
    }
