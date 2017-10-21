    @Test
    public void testStopStartOfHandler() throws Exception
    {
        URI destUri = serverUri.resolve("/info/");
        
        try (BlockheadClient client = new BlockheadClient(destUri))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();
            
            client.write(new TextFrame().setPayload("hello 1"));
            
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1000, TimeUnit.MILLISECONDS);
            String payload = frames.poll().getPayloadAsUTF8();
            
            // If we can connect and send a text message, we know that the endpoint was
            // added properly, and the response will help us verify the policy configuration too
            assertThat("payload", payload, containsString("session.maxTextMessageSize=" + (10 * 1024 * 1024)));
        }
        
        server.getHandler().stop();
        server.getHandler().start();
        
        try (BlockheadClient client = new BlockheadClient(destUri))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();
        
            client.write(new TextFrame().setPayload("hello 2"));
        
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1000, TimeUnit.MILLISECONDS);
            String payload = frames.poll().getPayloadAsUTF8();
        
            // If we can connect and send a text message, we know that the endpoint was
            // added properly, and the response will help us verify the policy configuration too
            assertThat("payload", payload, containsString("session.maxTextMessageSize=" + (10 * 1024 * 1024)));
        }
    }
