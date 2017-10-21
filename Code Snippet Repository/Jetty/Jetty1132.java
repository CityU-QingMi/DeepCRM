    @Test
    public void testGenerateParse() throws Exception
    {
        PriorityGenerator generator = new PriorityGenerator(new HeaderGenerator());

        final List<PriorityFrame> frames = new ArrayList<>();
        Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter()
        {
            @Override
            public void onPriority(PriorityFrame frame)
            {
                frames.add(frame);
            }
        }, 4096, 8192);

        int streamId = 13;
        int parentStreamId = 17;
        int weight = 256;
        boolean exclusive = true;

        // Iterate a few times to be sure generator and parser are properly reset.
        for (int i = 0; i < 2; ++i)
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.generatePriority(lease, streamId, parentStreamId, weight, exclusive);

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
        PriorityFrame frame = frames.get(0);
        Assert.assertEquals(streamId, frame.getStreamId());
        Assert.assertEquals(parentStreamId, frame.getParentStreamId());
        Assert.assertEquals(weight, frame.getWeight());
        Assert.assertEquals(exclusive, frame.isExclusive());
    }
