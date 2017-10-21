    @Test
    public void testUpgradeWithAuthorizationHeader() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();
        
        URI wsUri = server.getWsUri();
        ClientUpgradeRequest upgradeRequest = new ClientUpgradeRequest();
        // actual value for this test is irrelevant, its important that this
        // header actually be sent with a value (the value specified)
        upgradeRequest.setHeader("Authorization", "Bogus SHA1");
        Future<Session> future = client.connect(wsocket,wsUri,upgradeRequest);
        
        IBlockheadServerConnection connection = server.accept();
        List<String> requestLines = connection.upgrade();
    
        Session sess = future.get(30,TimeUnit.SECONDS);
        sess.close();

        String authLine = requestLines.stream()
                .filter((line) -> line.startsWith("Authorization:"))
                .findFirst().get();
        
        assertThat("Request Container Authorization", authLine, is("Authorization: Bogus SHA1"));
        assertThat("Connect.UpgradeRequest", wsocket.connectUpgradeRequest, notNullValue());
        assertThat("Connect.UpgradeResponse", wsocket.connectUpgradeResponse, notNullValue());
    }
