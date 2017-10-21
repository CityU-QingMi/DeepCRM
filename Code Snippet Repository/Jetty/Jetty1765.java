    @Test
    public void testRenegotiateLimit() throws Exception
    {
        __sslCtxFactory.setRenegotiationAllowed(true);
        __sslCtxFactory.setRenegotiationLimit(2);
        
        SSLSocket client = newClient();
        client.setSoTimeout(60000);

        SocketChannel server = _connector.accept();
        server.configureBlocking(false);
        _manager.accept(server);

        client.getOutputStream().write("Good".getBytes(StandardCharsets.UTF_8));
        byte[] buffer = new byte[1024];
        int len=client.getInputStream().read(buffer);
        Assert.assertEquals(4, len);
        Assert.assertEquals("Good",new String(buffer,0,len,StandardCharsets.UTF_8));
        
        client.startHandshake();

        client.getOutputStream().write("Bye".getBytes(StandardCharsets.UTF_8));
        len=client.getInputStream().read(buffer);
        Assert.assertEquals(3, len);
        Assert.assertEquals("Bye",new String(buffer,0,len,StandardCharsets.UTF_8));
        
        client.startHandshake();

        client.getOutputStream().write("Cruel".getBytes(StandardCharsets.UTF_8));
        len=client.getInputStream().read(buffer);
        Assert.assertEquals(5, len);
        Assert.assertEquals("Cruel",new String(buffer,0,len,StandardCharsets.UTF_8));

        client.startHandshake();
        
        client.getOutputStream().write("World".getBytes(StandardCharsets.UTF_8));
        try
        {
            client.getInputStream().read(buffer);
            Assert.fail();
        }
        catch(SSLException e)
        {
            // expected
        }
    }
