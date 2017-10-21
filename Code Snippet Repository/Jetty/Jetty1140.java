    @Test
    public void testGenerateParse() throws Exception
    {
        WindowUpdateGenerator generator = new WindowUpdateGenerator(new HeaderGenerator());

        final List<WindowUpdateFrame> frames = new ArrayList<>();
        Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter()
        {
            @Override
            public void onWindowUpdate(WindowUpdateFrame frame)
            {
                frames.add(frame);
            }
        }, 4096, 8192);

        int streamId = 13;
        int windowUpdate = 17;

        // Iterate a few times to be sure generator and parser are properly reset.
        for (int i = 0; i < 2; ++i)
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.generateWindowUpdate(lease, streamId, windowUpdate);

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
        WindowUpdateFrame frame = frames.get(0);
        Assert.assertEquals(streamId, frame.getStreamId());
        Assert.assertEquals(windowUpdate, frame.getWindowDelta());
    }
