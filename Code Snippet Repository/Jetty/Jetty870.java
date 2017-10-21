    private void testGenerateRequestContent(final int contentLength) throws Exception
    {
        ByteBuffer content = ByteBuffer.allocate(contentLength);

        ByteBufferPool byteBufferPool = new MappedByteBufferPool();
        ClientGenerator generator = new ClientGenerator(byteBufferPool);
        final int id = 13;
        Generator.Result result = generator.generateRequestContent(id, content, true, null);

        final AtomicInteger totalLength = new AtomicInteger();
        ServerParser parser = new ServerParser(new ServerParser.Listener.Adapter()
        {
            @Override
            public boolean onContent(int request, FCGI.StreamType stream, ByteBuffer buffer)
            {
                Assert.assertEquals(id, request);
                totalLength.addAndGet(buffer.remaining());
                return false;
            }

            @Override
            public void onEnd(int request)
            {
                Assert.assertEquals(id, request);
                Assert.assertEquals(contentLength, totalLength.get());
            }
        });

        for (ByteBuffer buffer : result.getByteBuffers())
        {
            parser.parse(buffer);
            Assert.assertFalse(buffer.hasRemaining());
        }

        // Parse again one byte at a time
        for (ByteBuffer buffer : result.getByteBuffers())
        {
            buffer.flip();
            while (buffer.hasRemaining())
                parser.parse(ByteBuffer.wrap(new byte[]{buffer.get()}));
            Assert.assertFalse(buffer.hasRemaining());
        }
    }
