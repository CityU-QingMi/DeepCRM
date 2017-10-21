    @Test
    public void testUniqueUserPropsConfigurator() throws Exception
    {
        URI uri = baseServerUri.resolve("/unique-user-props");

        // First request
        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            client.write(new TextFrame().setPayload("apple"));
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1, TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            Assert.assertThat("Frame Response", frame.getPayloadAsUTF8(), is("Requested User Property: [apple] = \"fruit from tree\""));
        }
        
        // Second request
        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            client.write(new TextFrame().setPayload("apple"));
            client.write(new TextFrame().setPayload("blueberry"));
            EventQueue<WebSocketFrame> frames = client.readFrames(2, 1, TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            // should have no value
            Assert.assertThat("Frame Response", frame.getPayloadAsUTF8(), is("Requested User Property: [apple] = <null>"));
            
            frame = frames.poll();
            Assert.assertThat("Frame Response", frame.getPayloadAsUTF8(), is("Requested User Property: [blueberry] = \"fruit from bush\""));
        }
    }
