    @Test(timeout=60000)
    public void testHandshakeThenReset() throws Exception
    {
        final SSLSocket client = newClient();

        SimpleProxy.AutomaticFlow automaticProxyFlow = proxy.startAutomaticFlow();
        client.startHandshake();
        Assert.assertTrue(automaticProxyFlow.stop(5, TimeUnit.SECONDS));

        proxy.sendRSTToServer();

        // Wait a while to detect spinning
        TimeUnit.MILLISECONDS.sleep(500);
        Assert.assertThat(sslFills.get(), Matchers.lessThan(20));
        Assert.assertThat(sslFlushes.get(), Matchers.lessThan(20));
        Assert.assertThat(httpParses.get(), Matchers.lessThan(20));

        client.close();
    }
