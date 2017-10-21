    @Test
    public void testGenerateParseInvalidSettings() throws Exception
    {
        SettingsGenerator generator = new SettingsGenerator(new HeaderGenerator());

        final AtomicInteger errorRef = new AtomicInteger();
        Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter()
        {
            @Override
            public void onConnectionFailure(int error, String reason)
            {
                errorRef.set(error);
            }
        }, 4096, 8192);

        Map<Integer, Integer> settings1 = new HashMap<>();
        settings1.put(13, 17);
        ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
        generator.generateSettings(lease, settings1, true);
        // Modify the length of the frame to make it invalid
        ByteBuffer bytes = lease.getByteBuffers().get(0);
        bytes.putShort(1, (short)(bytes.getShort(1) - 1));

        for (ByteBuffer buffer : lease.getByteBuffers())
        {
            while (buffer.hasRemaining())
            {
                parser.parse(ByteBuffer.wrap(new byte[]{buffer.get()}));
            }
        }

        Assert.assertEquals(ErrorCode.FRAME_SIZE_ERROR.code, errorRef.get());
    }
