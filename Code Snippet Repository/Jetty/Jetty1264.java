    @Test
    public void testRequestWithContinuationFramesWithEmptyLastContinuationFrame() throws Exception
    {
        testRequestWithContinuationFrames(null, () ->
        {
            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.control(lease, new PrefaceFrame());
            generator.control(lease, new SettingsFrame(new HashMap<>(), false));
            MetaData.Request metaData = newRequest("GET", new HttpFields());
            generator.control(lease, new HeadersFrame(1, metaData, null, true));
            // Take the last CONTINUATION frame and reset the flag.
            List<ByteBuffer> buffers = lease.getByteBuffers();
            ByteBuffer continuationFrameHeader = buffers.get(buffers.size() - 2);
            continuationFrameHeader.put(4, (byte)0);
            // Add a last, empty, CONTINUATION frame.
            ByteBuffer last = ByteBuffer.wrap(new byte[]{
                    0, 0, 0, // Length
                    (byte)FrameType.CONTINUATION.getType(),
                    (byte)Flags.END_HEADERS,
                    0, 0, 0, 1 // Stream ID
            });
            lease.append(last, false);
            return lease;
        });
    }
