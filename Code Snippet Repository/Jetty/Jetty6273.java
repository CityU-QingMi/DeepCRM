    @Test
    public void testCaptureRequestHeadersConfigurator() throws Exception
    {
        URI uri = baseServerUri.resolve("/capture-request-headers");

        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.addHeader("X-Dummy: Bogus\r\n");
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            client.write(new TextFrame().setPayload("X-Dummy"));
            EventQueue<WebSocketFrame> frames = client.readFrames(1, 1, TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            Assert.assertThat("Frame Response", frame.getPayloadAsUTF8(), is("Request Header [X-Dummy]: \"Bogus\""));
        }
    }
