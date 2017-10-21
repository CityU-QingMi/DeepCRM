    @Test
    public void testUserPropsAddress() throws Exception
    {
        URI uri = baseServerUri.resolve("/addr");

        // First request
        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();
            
            InetSocketAddress expectedLocal = client.getLocalSocketAddress();
            InetSocketAddress expectedRemote = client.getRemoteSocketAddress();

            client.write(new TextFrame().setPayload("addr"));
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1, TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            
            StringWriter expected = new StringWriter();
            PrintWriter out = new PrintWriter(expected);
            // local <-> remote are opposite on server (duh)
            out.printf("[javax.websocket.endpoint.localAddress] = %s%n", toSafeAddr(expectedRemote));
            out.printf("[javax.websocket.endpoint.remoteAddress] = %s%n", toSafeAddr(expectedLocal));
            out.printf("[found.local] = %s%n", toSafeAddr(expectedRemote));
            out.printf("[found.remote] = %s%n", toSafeAddr(expectedLocal));
            
            Assert.assertThat("Frame Response", frame.getPayloadAsUTF8(), is(expected.toString()));
        }
    }
