    @Test
    public void testMaxMessageSize() throws Exception
    {
        MaxMessageSocket wsocket = new MaxMessageSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection ssocket = server.accept();
        ssocket.upgrade();

        wsocket.awaitConnect(1,TimeUnit.SECONDS);

        Session sess = future.get(30,TimeUnit.SECONDS);
        Assert.assertThat("Session",sess,notNullValue());
        Assert.assertThat("Session.open",sess.isOpen(),is(true));

        // Create string that is larger than default size of 64k
        // but smaller than maxMessageSize of 100k
        byte buf[] = new byte[80 * 1024];
        Arrays.fill(buf,(byte)'x');
        String msg = StringUtil.toUTF8String(buf,0,buf.length);

        wsocket.getSession().getRemote().sendStringByFuture(msg);
        ssocket.echoMessage(1,2,TimeUnit.SECONDS);
        // wait for response from server
        wsocket.waitForMessage(1,TimeUnit.SECONDS);

        wsocket.assertMessage(msg);

        Assert.assertTrue(wsocket.dataLatch.await(2,TimeUnit.SECONDS));
    }
