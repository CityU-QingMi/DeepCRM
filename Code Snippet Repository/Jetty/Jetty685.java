    @Test(timeout=60000)
    public void testClientHelloThenReset() throws Exception
    {
        final SSLSocket client = newClient();

        threadPool.submit(() ->
        {
            client.startHandshake();
            return null;
        });

        // Client Hello
        TLSRecord record = proxy.readFromClient();
        Assert.assertNotNull(record);
        proxy.flushToServer(record);

        proxy.sendRSTToServer();

        // Wait a while to detect spinning
        TimeUnit.MILLISECONDS.sleep(500);
        Assert.assertThat(sslFills.get(), Matchers.lessThan(20));
        Assert.assertThat(sslFlushes.get(), Matchers.lessThan(20));
        Assert.assertThat(httpParses.get(), Matchers.lessThan(20));

        client.close();
    }
