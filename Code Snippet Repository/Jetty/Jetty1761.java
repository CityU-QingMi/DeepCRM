    @Test
    public void testShutdown() throws Exception
    {
        Socket client = newClient();

        client.setSoTimeout(500);

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

        // wait for read timeout
        long start = System.currentTimeMillis();
        try
        {
            client.getInputStream().read();
            Assert.fail();
        }
        catch (SocketTimeoutException e)
        {
            assertTrue(System.currentTimeMillis() - start >= 400);
        }

        // write then shutdown
        client.getOutputStream().write("Goodbye Cruel TLS".getBytes(StandardCharsets.UTF_8));
        client.shutdownOutput();

        // Verify echo server to client
        for (char c : "Goodbye Cruel TLS".toCharArray())
        {
            int b = client.getInputStream().read();
            assertTrue(b > 0);
            assertEquals(c, (char)b);
        }

        // Read close
        assertEquals(-1, client.getInputStream().read());
    }
