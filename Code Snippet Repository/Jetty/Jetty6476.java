    @Test
    public void testBasicEcho_FromClient() throws Exception
    {
        JettyTrackingSocket cliSock = new JettyTrackingSocket();

        client.getPolicy().setIdleTimeout(10000);

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

        Collection<WebSocketSession> sessions = client.getOpenSessions();
        Assert.assertThat("client.connectionManager.sessions.size",sessions.size(),is(1));

        RemoteEndpoint remote = cliSock.getSession().getRemote();
        remote.sendStringByFuture("Hello World!");
        if (remote.getBatchMode() == BatchMode.ON)
            remote.flush();
        srvSock.echoMessage(1,30,TimeUnit.SECONDS);
        // wait for response from server
        cliSock.waitForMessage(30,TimeUnit.SECONDS);

        cliSock.assertMessage("Hello World!");
    }
