    @Test(expected = IllegalArgumentException.class)
    public void testAddExtension_NotInstalled() throws Exception
    {
        JettyTrackingSocket cliSock = new JettyTrackingSocket();

        client.getPolicy().setIdleTimeout(10000);

        URI wsUri = server.getWsUri();
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        request.setSubProtocols("echo");
        request.addExtensions("x-bad");

        // Should trigger failure on bad extension
        client.connect(cliSock,wsUri,request);
    }
