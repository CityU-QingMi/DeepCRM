    @Test
    public void testGenerateParse() throws Exception
    {
        GoAwayGenerator generator = new GoAwayGenerator(new HeaderGenerator());

        final List<GoAwayFrame> frames = new ArrayList<>();
        Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter()
        {
            @Override
            public void onGoAway(GoAwayFrame frame)
            {
                frames.add(frame);
            }
        }, 4096, 8192);

        int lastStreamId = 13;
        int error = 17;

        // Iterate a few times to be sure generator and parser are properly reset.
        for (int i = 0; i < 2; ++i)
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.generateGoAway(lease, lastStreamId, error, null);

            frames.clear();
            for (ByteBuffer buffer : lease.getByteBuffers())
            {
                while (buffer.hasRemaining())
                {
                    parser.parse(buffer);
                }
            }
        }

        Assert.assertEquals(1, frames.size());
        GoAwayFrame frame = frames.get(0);
        Assert.assertEquals(lastStreamId, frame.getLastStreamId());
        Assert.assertEquals(error, frame.getError());
        Assert.assertNull(frame.getPayload());
    }
