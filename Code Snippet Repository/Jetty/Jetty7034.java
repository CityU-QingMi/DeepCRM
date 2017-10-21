    @Test
    public void testUpgradeRequestResponse() throws Exception
    {
        URI uri = server.getServerUri().resolve("/test?snack=cashews&amount=handful&brand=off");
        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            // Ask the server socket for specific parameter map info
            client.write(new TextFrame().setPayload("getParameterMap|snack"));
            client.write(new TextFrame().setPayload("getParameterMap|amount"));
            client.write(new TextFrame().setPayload("getParameterMap|brand"));
            client.write(new TextFrame().setPayload("getParameterMap|cost")); // intentionally invalid

            // Read frame (hopefully text frame)
            EventQueue<WebSocketFrame> frames = client.readFrames(4,5,TimeUnit.SECONDS);
            WebSocketFrame tf = frames.poll();
            Assert.assertThat("Parameter Map[snack]", tf.getPayloadAsUTF8(), is("[cashews]"));
            tf = frames.poll();
            Assert.assertThat("Parameter Map[amount]", tf.getPayloadAsUTF8(), is("[handful]"));
            tf = frames.poll();
            Assert.assertThat("Parameter Map[brand]", tf.getPayloadAsUTF8(), is("[off]"));
            tf = frames.poll();
            Assert.assertThat("Parameter Map[cost]", tf.getPayloadAsUTF8(), is("<null>"));
        }
    }
