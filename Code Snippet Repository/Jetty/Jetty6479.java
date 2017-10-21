    @Test
    public void testLocalRemoteAddress() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection ssocket = server.accept();
        ssocket.upgrade();

        future.get(30,TimeUnit.SECONDS);

        Assert.assertTrue(wsocket.openLatch.await(1,TimeUnit.SECONDS));

        InetSocketAddress local = wsocket.getSession().getLocalAddress();
        InetSocketAddress remote = wsocket.getSession().getRemoteAddress();

        Assert.assertThat("Local Socket Address",local,notNullValue());
        Assert.assertThat("Remote Socket Address",remote,notNullValue());

        // Hard to validate (in a portable unit test) the local address that was used/bound in the low level Jetty Endpoint
        Assert.assertThat("Local Socket Address / Host",local.getAddress().getHostAddress(),notNullValue());
        Assert.assertThat("Local Socket Address / Port",local.getPort(),greaterThan(0));

        Assert.assertThat("Remote Socket Address / Host",remote.getAddress().getHostAddress(),is(wsUri.getHost()));
        Assert.assertThat("Remote Socket Address / Port",remote.getPort(),greaterThan(0));
    }
