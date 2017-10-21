    @Test
    public void testGenerateParseOneByteAtATime() throws Exception
    {
        SettingsGenerator generator = new SettingsGenerator(new HeaderGenerator());

        final List<SettingsFrame> frames = new ArrayList<>();
        Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter()
        {
            @Override
            public void onSettings(SettingsFrame frame)
            {
                frames.add(frame);
            }
        }, 4096, 8192);

        Map<Integer, Integer> settings1 = new HashMap<>();
        int key = 13;
        Integer value = 17;
        settings1.put(key, value);

        // Iterate a few times to be sure generator and parser are properly reset.
        for (int i = 0; i < 2; ++i)
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.generateSettings(lease, settings1, true);

            frames.clear();
            for (ByteBuffer buffer : lease.getByteBuffers())
            {
                while (buffer.hasRemaining())
                {
                    parser.parse(ByteBuffer.wrap(new byte[]{buffer.get()}));
                }
            }

            Assert.assertEquals(1, frames.size());
            SettingsFrame frame = frames.get(0);
            Map<Integer, Integer> settings2 = frame.getSettings();
            Assert.assertEquals(1, settings2.size());
            Assert.assertEquals(value, settings2.get(key));
            Assert.assertTrue(frame.isReply());
        }
    }
