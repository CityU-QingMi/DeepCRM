    @Test
    public void testRequestWithContinuationFramesWithEmptyContinuationFrame() throws Exception
    {
        testRequestWithContinuationFrames(null, () ->
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.control(lease, new PrefaceFrame());
            generator.control(lease, new SettingsFrame(new HashMap<>(), false));
            MetaData.Request metaData = newRequest("GET", new HttpFields());
            generator.control(lease, new HeadersFrame(1, metaData, null, true));
            // Take the ContinuationFrame header, duplicate it, and set the length to zero.
            List<ByteBuffer> buffers = lease.getByteBuffers();
            ByteBuffer continuationFrameHeader = buffers.get(4);
            ByteBuffer duplicate = ByteBuffer.allocate(continuationFrameHeader.remaining());
            duplicate.put(continuationFrameHeader).flip();
            continuationFrameHeader.flip();
            continuationFrameHeader.put(0, (byte)0);
            continuationFrameHeader.putShort(1, (short)0);
            // Insert a CONTINUATION frame header for the body of the previous CONTINUATION frame.
            lease.insert(5, duplicate, false);
            return lease;
        });
    }
