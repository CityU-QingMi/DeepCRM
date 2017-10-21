    @Test
    public void testParseNoResponseContent() throws Exception
    {
        final int id = 13;
        HttpFields fields = new HttpFields();
        fields.put("Content-Length", "0");

        ByteBufferPool byteBufferPool = new MappedByteBufferPool();
        ServerGenerator generator = new ServerGenerator(byteBufferPool);
        Generator.Result result1 = generator.generateResponseHeaders(id, 200, "OK", fields, null);
        Generator.Result result2 = generator.generateResponseContent(id, null, true, false, null);

        final AtomicInteger verifier = new AtomicInteger();
        ClientParser parser = new ClientParser(new ClientParser.Listener.Adapter()
        {
            @Override
            public boolean onContent(int request, FCGI.StreamType stream, ByteBuffer buffer)
            {
                Assert.assertEquals(id, request);
                verifier.addAndGet(2);
                return false;
            }

            @Override
            public void onEnd(int request)
            {
                Assert.assertEquals(id, request);
                verifier.addAndGet(3);
            }
        });

        for (ByteBuffer buffer : result1.getByteBuffers())
        {
            parser.parse(buffer);
            Assert.assertFalse(buffer.hasRemaining());
        }
        for (ByteBuffer buffer : result2.getByteBuffers())
        {
            parser.parse(buffer);
            Assert.assertFalse(buffer.hasRemaining());
        }

        Assert.assertEquals(3, verifier.get());
    }
