    @Test
    public void testDisconnect() throws Exception
    {
        URI uri = server.getServerUri().resolve("/test/disconnect");
        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            client.write(new TextFrame().setPayload("harsh-disconnect"));

            client.awaitDisconnect(1, TimeUnit.SECONDS);
        }
    }
