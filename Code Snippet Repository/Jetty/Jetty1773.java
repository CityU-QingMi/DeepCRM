    @Test
    public void testHelloWorld() throws Exception
    {
        Socket client = newClient();
        client.setSoTimeout(60000);

        SocketChannel server = _connector.accept();
        server.configureBlocking(false);
        _manager.accept(server);

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

        client.close();
    }
