    @Test
    public void testUpgradeRequest() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection connection = server.accept();
        connection.upgrade();

        Session sess = future.get(30,TimeUnit.SECONDS);
        
        wsocket.waitForConnected(1, TimeUnit.SECONDS);
        
        assertThat("Connect.UpgradeRequest", wsocket.connectUpgradeRequest, notNullValue());
        assertThat("Connect.UpgradeResponse", wsocket.connectUpgradeResponse, notNullValue());
        
        sess.close();
    }
