    @Test
    public void testReadBlocked() throws Exception
    {
        Socket client = newClient();

        SocketChannel server = _connector.accept();
        server.configureBlocking(false);

        _manager.accept(server);

        OutputStream clientOutputStream = client.getOutputStream();
        InputStream clientInputStream = client.getInputStream();

        int specifiedTimeout = 1000;
        client.setSoTimeout(specifiedTimeout);

        // Write 8 and cause block waiting for 10
        _blockAt = 10;
        clientOutputStream.write("12345678".getBytes(StandardCharsets.UTF_8));
        clientOutputStream.flush();

        Assert.assertTrue(_lastEndPointLatch.await(1, TimeUnit.SECONDS));
        _lastEndPoint.setIdleTimeout(10 * specifiedTimeout);
        Thread.sleep((11 * specifiedTimeout) / 10);

        long start = System.currentTimeMillis();
        try
        {
            int b = clientInputStream.read();
            Assert.fail("Should have timed out waiting for a response, but read " + b);
        }
        catch (SocketTimeoutException e)
        {
            int elapsed = Long.valueOf(System.currentTimeMillis() - start).intValue();
            Assert.assertThat("Expected timeout", elapsed, greaterThanOrEqualTo(3 * specifiedTimeout / 4));
        }

        // write remaining characters
        clientOutputStream.write("90ABCDEF".getBytes(StandardCharsets.UTF_8));
        clientOutputStream.flush();

        // Verify echo server to client
        for (char c : "1234567890ABCDEF".toCharArray())
        {
            int b = clientInputStream.read();
            assertTrue(b > 0);
            assertEquals(c, (char)b);
        }
    }
