    @Test
    public void testIdle() throws Exception
    {
        Socket client = newClient();

        client.setSoTimeout(3000);

        SocketChannel server = _connector.accept();
        server.configureBlocking(false);

        _manager.accept(server);

        // Write client to server
        client.getOutputStream().write("HelloWorld".getBytes(StandardCharsets.UTF_8));

        // Verify echo server to client
        for (char c : "HelloWorld".toCharArray())
        {
            int b = client.getInputStream().read();
            assertTrue(b > 0);
            assertEquals(c, (char)b);
        }

        Assert.assertTrue(_lastEndPointLatch.await(1, TimeUnit.SECONDS));
        int idleTimeout = 500;
        _lastEndPoint.setIdleTimeout(idleTimeout);

        // read until idle shutdown received
        long start = System.currentTimeMillis();
        int b = client.getInputStream().read();
        assertEquals(-1, b);
        long idle = System.currentTimeMillis() - start;
        assertTrue(idle > idleTimeout / 2);
        assertTrue(idle < idleTimeout * 2);

        // But endpoint may still be open for a little bit.
        for (int i = 0; i < 10; ++i)
        {
            if (_lastEndPoint.isOpen())
                Thread.sleep(2 * idleTimeout / 10);
            else
                break;
        }
        assertFalse(_lastEndPoint.isOpen());
    }
