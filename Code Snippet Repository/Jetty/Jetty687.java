    @Test(timeout=60000)
    public void testRequestIncompleteThenReset() throws Exception
    {
        final SSLSocket client = newClient();

        SimpleProxy.AutomaticFlow automaticProxyFlow = proxy.startAutomaticFlow();
        client.startHandshake();
        Assert.assertTrue(automaticProxyFlow.stop(5, TimeUnit.SECONDS));

        threadPool.submit(() ->
        {
            OutputStream clientOutput = client.getOutputStream();
            clientOutput.write(("" +
                    "GET / HTTP/1.1\r\n" +
                    "Host: localhost\r\n" +
                    "\r\n").getBytes(StandardCharsets.UTF_8));
            clientOutput.flush();
            return null;
        });

        // Application data
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
