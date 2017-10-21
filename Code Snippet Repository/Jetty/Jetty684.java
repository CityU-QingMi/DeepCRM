    @Test(timeout=60000)
    public void testClientHelloIncompleteThenReset() throws Exception
    {
        final SSLSocket client = newClient();

        threadPool.submit(() ->
        {
            client.startHandshake();
            return null;
        });

        // Client Hello
        TLSRecord record = proxy.readFromClient();
        byte[] bytes = record.getBytes();
        byte[] chunk1 = new byte[2 * bytes.length / 3];
        System.arraycopy(bytes, 0, chunk1, 0, chunk1.length);
        proxy.flushToServer(100, chunk1);

        proxy.sendRSTToServer();

        // Wait a while to detect spinning
        TimeUnit.MILLISECONDS.sleep(500);
        Assert.assertThat(sslFills.get(), Matchers.lessThan(20));
        Assert.assertThat(sslFlushes.get(), Matchers.lessThan(20));
        Assert.assertThat(httpParses.get(), Matchers.lessThan(20));

        client.close();
    }
