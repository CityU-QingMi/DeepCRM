    @Test
    public void testPayloadAsLong() throws Exception
    {
        PingGenerator generator = new PingGenerator(new HeaderGenerator());

        final List<PingFrame> frames = new ArrayList<>();
        Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter()
        {
            @Override
            public void onPing(PingFrame frame)
            {
                frames.add(frame);
            }
        }, 4096, 8192);

        ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
        PingFrame ping = new PingFrame(System.nanoTime(), true);
        generator.generate(lease, ping);

        for (ByteBuffer buffer : lease.getByteBuffers())
        {
            while (buffer.hasRemaining())
            {
                parser.parse(buffer);
            }
        }

        Assert.assertEquals(1, frames.size());
        PingFrame pong = frames.get(0);
        Assert.assertEquals(ping.getPayloadAsLong(), pong.getPayloadAsLong());
        Assert.assertTrue(pong.isReply());
    }
