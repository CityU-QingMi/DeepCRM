    @Test
    public void testBasicEcho_UsingCallback() throws Exception
    {
        client.setMaxIdleTimeout(160000);
        JettyTrackingSocket cliSock = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        request.setSubProtocols("echo");
        Future<Session> future = client.connect(cliSock,wsUri,request);

        final IBlockheadServerConnection srvSock = server.accept();
        srvSock.upgrade();

        Session sess = future.get(30,TimeUnit.SECONDS);
        Assert.assertThat("Session",sess,notNullValue());
        Assert.assertThat("Session.open",sess.isOpen(),is(true));
        Assert.assertThat("Session.upgradeRequest",sess.getUpgradeRequest(),notNullValue());
        Assert.assertThat("Session.upgradeResponse",sess.getUpgradeResponse(),notNullValue());

        cliSock.assertWasOpened();
        cliSock.assertNotClosed();

        Collection<WebSocketSession> sessions = client.getBeans(WebSocketSession.class);
        Assert.assertThat("client.connectionManager.sessions.size",sessions.size(),is(1));

        FutureWriteCallback callback = new FutureWriteCallback();

        cliSock.getSession().getRemote().sendString("Hello World!",callback);
        callback.get(1,TimeUnit.SECONDS);
    }
