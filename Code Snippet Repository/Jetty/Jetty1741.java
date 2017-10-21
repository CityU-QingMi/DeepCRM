    @Test
    public void testAsyncSocketChannel() throws Exception
    {
        AsynchronousServerSocketChannel connector = AsynchronousServerSocketChannel.open();
        connector.bind(null);
        InetSocketAddress addr=(InetSocketAddress)connector.getLocalAddress();
        Future<AsynchronousSocketChannel> acceptor = connector.accept();

        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();

        client.connect(new InetSocketAddress("127.0.0.1",addr.getPort())).get(5, TimeUnit.SECONDS);

        AsynchronousSocketChannel server = acceptor.get(5, TimeUnit.SECONDS);

        ByteBuffer read = ByteBuffer.allocate(1024);
        Future<Integer> reading = server.read(read);

        byte[] data = "Testing 1 2 3".getBytes(StandardCharsets.UTF_8);
        ByteBuffer write = BufferUtil.toBuffer(data);
        Future<Integer> writing = client.write(write);

        writing.get(5, TimeUnit.SECONDS);
        reading.get(5, TimeUnit.SECONDS);
        read.flip();

        Assert.assertEquals(ByteBuffer.wrap(data), read);
    }
