    @Test
    public void testLowercaseUpgrade() throws Exception
    {
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        try
        {
            client.connect();

            StringBuilder req = new StringBuilder();
            req.append("GET ").append(client.getRequestPath()).append(" HTTP/1.1\r\n");
            req.append("Host: ").append(client.getRequestHost()).append("\r\n");
            req.append("Upgrade: websocket\r\n");
            req.append("connection: upgrade\r\n");
            req.append("sec-websocket-key: ").append(client.getRequestWebSocketKey()).append("\r\n");
            req.append("sec-websocket-origin: ").append(client.getRequestWebSocketOrigin()).append("\r\n");
            req.append("sec-websocket-protocol: echo\r\n");
            req.append("sec-websocket-version: 13\r\n");
            req.append("\r\n");
            client.writeRaw(req.toString());

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
