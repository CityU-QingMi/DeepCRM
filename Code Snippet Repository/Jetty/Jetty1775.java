    @Test
    public void testRenegotiateNotAllowed() throws Exception
    {
        __sslCtxFactory.setRenegotiationAllowed(false);
        
        SSLSocket client = newClient();
        client.setSoTimeout(60000);

        SocketChannel server = _connector.accept();
        server.configureBlocking(false);
        _manager.accept(server);

        client.getOutputStream().write("Hello".getBytes(StandardCharsets.UTF_8));
        byte[] buffer = new byte[1024];
        int len=client.getInputStream().read(buffer);
        Assert.assertEquals(5, len);
        Assert.assertEquals("Hello",new String(buffer,0,len,StandardCharsets.UTF_8));

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
