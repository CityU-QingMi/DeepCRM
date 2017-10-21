    @Test
    public void testBasicEcho_FromServer() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();
        Future<Session> future = client.connect(wsocket,server.getWsUri());

        // Server
        final IBlockheadServerConnection srvSock = server.accept();
        srvSock.upgrade();

        // Validate connect
        Session sess = future.get(30,TimeUnit.SECONDS);
        Assert.assertThat("Session",sess,notNullValue());
        Assert.assertThat("Session.open",sess.isOpen(),is(true));
        Assert.assertThat("Session.upgradeRequest",sess.getUpgradeRequest(),notNullValue());
        Assert.assertThat("Session.upgradeResponse",sess.getUpgradeResponse(),notNullValue());

        // Have server send initial message
        srvSock.write(new TextFrame().setPayload("Hello World"));

        // Verify connect
        future.get(30,TimeUnit.SECONDS);
        wsocket.assertWasOpened();
        wsocket.awaitMessage(1,TimeUnit.SECONDS,2);

        wsocket.assertMessage("Hello World");
    }
