    @Test(timeout=60000)
    public void testPlainText() throws Exception
    {
        final SSLSocket client = newClient();

        threadPool.submit(() ->
        {
            client.startHandshake();
            return null;
        });

        // Instead of passing the Client Hello, we simulate plain text was passed in
        proxy.flushToServer(0, "GET / HTTP/1.1\r\n".getBytes(StandardCharsets.UTF_8));

        // We expect that the server sends the TLS Alert.
        TLSRecord record = proxy.readFromServer();
        Assert.assertNotNull(record);
        Assert.assertEquals(TLSRecord.Type.ALERT, record.getType());
        record = proxy.readFromServer();
        Assert.assertNull(record);

        // Check that we did not spin
        TimeUnit.MILLISECONDS.sleep(500);
        Assert.assertThat(sslFills.get(), Matchers.lessThan(20));
        Assert.assertThat(sslFlushes.get(), Matchers.lessThan(20));
        Assert.assertThat(httpParses.get(), Matchers.lessThan(20));

        client.close();
    }
