    @Test
    public void testAccessRequestCookies() throws Exception
    {
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        client.setTimeout(1,TimeUnit.SECONDS);

        try
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();
            
            client.write(new TextFrame().setPayload("info"));

            EventQueue<WebSocketFrame> frames = client.readFrames(1,1,TimeUnit.SECONDS);
            WebSocketFrame resp = frames.poll();
            String textMsg = resp.getPayloadAsUTF8();
            
            assertThat("DecoratedObjectFactory", textMsg, containsString("Object is a DecoratedObjectFactory"));
            assertThat("decorators.size", textMsg, containsString("Decorators.size = [1]"));
            assertThat("decorator type", textMsg, containsString("decorator[] = " + DummyLegacyDecorator.class.getName()));
        }
        finally
        {
            client.close();
        }
    }
