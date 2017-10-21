    private void testSubProtocol(String requestProtocols, String acceptedSubProtocols) throws Exception
    {
        try (BlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setTimeout(1, TimeUnit.SECONDS);
            
            client.connect();
            client.addHeader("Sec-WebSocket-Protocol: "+ requestProtocols + "\r\n");
            client.sendStandardRequest();
            client.expectUpgradeResponse();
            
            client.write(new TextFrame().setPayload("showme"));
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 30, TimeUnit.SECONDS);
            WebSocketFrame tf = frames.poll();
            
            assertThat(ProtocolEchoSocket.class.getSimpleName() + ".onMessage()", tf.getPayloadAsUTF8(), is("acceptedSubprotocol=" + acceptedSubProtocols));
        }
    }
