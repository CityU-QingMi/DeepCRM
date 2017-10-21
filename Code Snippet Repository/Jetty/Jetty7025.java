    @SuppressWarnings("")
    private void dropConnection() throws Exception
    {
        try (IBlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setProtocols("container");
            client.setTimeout(1,TimeUnit.SECONDS);
            try (StacklessLogging scope = new StacklessLogging(WebSocketSession.class))
            {
                client.connect();
                client.sendStandardRequest();
                client.expectUpgradeResponse();
                client.disconnect();
            }
        }
    }
