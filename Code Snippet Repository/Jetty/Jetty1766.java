    @Test
    public void testWriteOnConnect() throws Exception
    {
        _testFill=false;

        _writeCallback = new FutureCallback();
        Socket client = newClient();
        client.setSoTimeout(10000);

        SocketChannel server = _connector.accept();
        server.configureBlocking(false);
        _manager.accept(server);

        byte[] buffer = new byte[1024];
        int len=client.getInputStream().read(buffer);
        Assert.assertEquals("Hello Client",new String(buffer,0,len,StandardCharsets.UTF_8));
        Assert.assertEquals(null,_writeCallback.get(100,TimeUnit.MILLISECONDS));
        client.close();
    }
