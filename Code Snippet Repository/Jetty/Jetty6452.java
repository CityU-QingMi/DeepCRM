    @Test
    public void testAltConnect() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();
        URI wsUri = server.getWsUri();
        
        HttpClient httpClient = new HttpClient();
        httpClient.start();
        
        WebSocketUpgradeRequest req = new WebSocketUpgradeRequest(new WebSocketClient(), httpClient, wsUri, wsocket);
        req.header("X-Foo","Req");
        CompletableFuture<Session> sess = req.sendAsync();

        sess.thenAccept((s) -> {
            System.out.printf("Session: %s%n",s);
            s.close();
            assertThat("Connect.UpgradeRequest",wsocket.connectUpgradeRequest,notNullValue());
            assertThat("Connect.UpgradeResponse",wsocket.connectUpgradeResponse,notNullValue());
        });
        
        IBlockheadServerConnection connection = server.accept();
        connection.upgrade();
    }
