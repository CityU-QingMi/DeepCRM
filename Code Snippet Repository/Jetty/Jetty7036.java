    @Test
    public void testInternalError() throws Exception
    {
        try (BlockheadClient client = new BlockheadClient(server.getServerUri());
             StacklessLogging stackless=new StacklessLogging(EventDriver.class))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            try (StacklessLogging context = new StacklessLogging(EventDriver.class))
            {
                // Generate text frame
                client.write(new TextFrame().setPayload("CRASH"));

                // Read frame (hopefully close frame)
                EventQueue<WebSocketFrame> frames = client.readFrames(1,30,TimeUnit.SECONDS);
                Frame cf = frames.poll();
                CloseInfo close = new CloseInfo(cf);
                Assert.assertThat("Close Frame.status code",close.getStatusCode(),is(StatusCode.SERVER_ERROR));
            }
        }
    }
