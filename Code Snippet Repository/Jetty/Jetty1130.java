    @Test
    public void testGenerateParseOneByteAtATime() throws Exception
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

        byte[] payload = new byte[8];
        new Random().nextBytes(payload);

        // Iterate a few times to be sure generator and parser are properly reset.
        for (int i = 0; i < 2; ++i)
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.generatePing(lease, payload, true);

            frames.clear();
            for (ByteBuffer buffer : lease.getByteBuffers())
            {
                while (buffer.hasRemaining())
                {
                    parser.parse(ByteBuffer.wrap(new byte[]{buffer.get()}));
                }
            }

            Assert.assertEquals(1, frames.size());
            PingFrame frame = frames.get(0);
            Assert.assertArrayEquals(payload, frame.getPayload());
            Assert.assertTrue(frame.isReply());
        }
    }
