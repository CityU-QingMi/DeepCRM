    private List<DataFrame> testGenerateParse(ByteBuffer data)
    {
        DataGenerator generator = new DataGenerator(new HeaderGenerator());

        final List<DataFrame> frames = new ArrayList<>();
        Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter()
        {
            @Override
            public void onData(DataFrame frame)
            {
                frames.add(frame);
            }
        }, 4096, 8192);

        // Iterate a few times to be sure generator and parser are properly reset.
        for (int i = 0; i < 2; ++i)
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            ByteBuffer slice = data.slice();
            int generated = 0;
            while (true)
            {
                generated += generator.generateData(lease, 13, slice, true, slice.remaining());
                generated -= Frame.HEADER_LENGTH;
                if (generated == data.remaining())
                    break;
            }

            frames.clear();
            for (ByteBuffer buffer : lease.getByteBuffers())
            {
                parser.parse(buffer);
            }
        }

        return frames;
    }
