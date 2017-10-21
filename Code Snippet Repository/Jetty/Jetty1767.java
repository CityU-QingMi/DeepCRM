    @Test
    public void testBlockedWrite() throws Exception
    {
        Socket client = newClient();
        client.setSoTimeout(5000);

        SocketChannel server = _connector.accept();
        server.configureBlocking(false);
        _manager.accept(server);

        __startBlocking.set(5);
        __blockFor.set(3);
        
        client.getOutputStream().write("Hello".getBytes(StandardCharsets.UTF_8));
        byte[] buffer = new byte[1024];
        int len=client.getInputStream().read(buffer);
        Assert.assertEquals(5, len);
        Assert.assertEquals("Hello",new String(buffer,0,len,StandardCharsets.UTF_8));

        _dispatches.set(0);
        client.getOutputStream().write("World".getBytes(StandardCharsets.UTF_8));
        len=5;
        while(len>0)
            len-=client.getInputStream().read(buffer);
        Assert.assertEquals(0, len);
        client.close();
    }
