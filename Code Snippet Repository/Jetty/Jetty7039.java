    @Test
    public void testUppercaseUpgrade() throws Exception
    {
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        try
        {
            client.connect();

            StringBuilder req = new StringBuilder();
            req.append("GET ").append(client.getRequestPath()).append(" HTTP/1.1\r\n");
            req.append("HOST: ").append(client.getRequestHost()).append("\r\n");
            req.append("UPGRADE: WEBSOCKET\r\n");
            req.append("CONNECTION: UPGRADE\r\n");
            req.append("SEC-WEBSOCKET-KEY: ").append(client.getRequestWebSocketKey()).append("\r\n");
            req.append("SEC-WEBSOCKET-ORIGIN: ").append(client.getRequestWebSocketOrigin()).append("\r\n");
            req.append("SEC-WEBSOCKET-PROTOCOL: ECHO\r\n");
            req.append("SEC-WEBSOCKET-VERSION: 13\r\n");
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
