    @Test
    public void testMessageBiggerThanBufferSize() throws Exception
    {
        int bufferSize = 512;

        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection ssocket = server.accept();
        ssocket.upgrade();

        future.get(30,TimeUnit.SECONDS);

        Assert.assertTrue(wsocket.openLatch.await(1,TimeUnit.SECONDS));

        int length = bufferSize + (bufferSize / 2); // 1.5 times buffer size
        ssocket.write(0x80 | 0x01); // FIN + TEXT
        ssocket.write(0x7E); // No MASK and 2 bytes length
        ssocket.write(length >> 8); // first length byte
        ssocket.write(length & 0xFF); // second length byte
        for (int i = 0; i < length; ++i)
        {
            ssocket.write('x');
        }
        ssocket.flush();

        Assert.assertTrue(wsocket.dataLatch.await(1000,TimeUnit.SECONDS));
    }
